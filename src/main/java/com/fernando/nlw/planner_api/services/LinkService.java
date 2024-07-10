package com.fernando.nlw.planner_api.services;

import java.util.UUID;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import com.fernando.nlw.planner_api.exceptions.EntityNotFoundException;
import com.fernando.nlw.planner_api.models.Link;
import com.fernando.nlw.planner_api.models.Trip;
import com.fernando.nlw.planner_api.repositories.LinkRepository;
import com.fernando.nlw.planner_api.repositories.TripRepository;
import com.fernando.nlw.planner_api.requests.LinkRequest;
import com.fernando.nlw.planner_api.responses.LinkCreatedResponse;
import com.fernando.nlw.planner_api.responses.LinksResponse;

@Service
@RequiredArgsConstructor
public class LinkService {
    private final LinkRepository linkRepository;
    private final TripRepository tripRepository;

    public LinkCreatedResponse createLink(Trip trip, LinkRequest request) {
        Link link = new Link(trip, request);

        linkRepository.save(link);

        return LinkCreatedResponse.builder()
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
            .map(link -> {
                return LinksResponse.LinkResponse.builder()
                    .id(link.getId())
                    .title(link.getTitle())
                    .url(link.getUrl())
                    .build();
            })
            .toList();

        return LinksResponse.builder()
            .tripID(tripID)
            .links(activites)
            .build();
    }
}