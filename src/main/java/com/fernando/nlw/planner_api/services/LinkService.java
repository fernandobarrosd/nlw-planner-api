package com.fernando.nlw.planner_api.services;

import java.util.Optional;
import java.util.UUID;

import com.fernando.nlw.planner_api.responses.LinkResponse;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import com.fernando.nlw.planner_api.exceptions.EntityNotFoundException;
import com.fernando.nlw.planner_api.models.Link;
import com.fernando.nlw.planner_api.models.Trip;
import com.fernando.nlw.planner_api.repositories.LinkRepository;
import com.fernando.nlw.planner_api.repositories.TripRepository;
import com.fernando.nlw.planner_api.requests.LinkRequest;
import com.fernando.nlw.planner_api.responses.LinksResponse;

@Service
@RequiredArgsConstructor
public class LinkService {
    private final LinkRepository linkRepository;
    private final TripRepository tripRepository;

    public LinkResponse createLink(Trip trip, LinkRequest request) {
        Link link = new Link(trip, request);

        linkRepository.save(link);

        return LinkResponse.builder()
            .id(link.getId())
            .tripID(trip.getId())
            .title(link.getTitle())
            .url(link.getUrl())
            .build();
    }

    public LinksResponse findAllLinksByTripId(UUID tripID) {
        if (tripRepository.findById(tripID).isEmpty()) {
            throw new EntityNotFoundException("The trip#%s is not exists".formatted(tripID));
        }
        var activites = linkRepository.findByTripId(tripID)
            .stream()
            .map(link -> LinkResponse.builder()
                .id(link.getId())
                .title(link.getTitle())
                .url(link.getUrl())
                .build())
            .toList();

        return LinksResponse.builder()
            .tripID(tripID)
            .links(activites)
            .build();
    }

    public LinkResponse findLinkByID(UUID linkID) {
        Optional<Link> linkOptional = linkRepository.findById(linkID);

        if (linkOptional.isEmpty()) {
            throw new EntityNotFoundException("The link is not exists");
        }
        Link link = linkOptional.get();
        return LinkResponse.builder()
                .id(link.getId())
                .title(link.getTitle())
                .url(link.getUrl())
                .tripID(link.getTrip().getId())
                .build();
    }
}