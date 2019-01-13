package insat.company.platform.web.rest;

import com.codahale.metrics.annotation.Timed;
import insat.company.platform.domain.InsatEvent;
import insat.company.platform.service.InsatEventService;
import insat.company.platform.web.rest.errors.BadRequestAlertException;
import insat.company.platform.web.rest.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * REST controller for managing InsatEvent.
 */
@RestController
@RequestMapping("/api")
public class InsatEventResource {

    private final Logger log = LoggerFactory.getLogger(InsatEventResource.class);

    private static final String ENTITY_NAME = "insatEvent";

    private final InsatEventService insatEventService;

    public InsatEventResource(InsatEventService insatEventService) {
        this.insatEventService = insatEventService;
    }

    /**
     * POST  /insat-events : Create a new insatEvent.
     *
     * @param insatEvent the insatEvent to create
     * @return the ResponseEntity with status 201 (Created) and with body the new insatEvent, or with status 400 (Bad Request) if the insatEvent has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/insat-events")
    @Timed
    public ResponseEntity<InsatEvent> createInsatEvent(@RequestBody InsatEvent insatEvent) throws URISyntaxException {
        log.debug("REST request to save InsatEvent : {}", insatEvent);
        if (insatEvent.getId() != null) {
            throw new BadRequestAlertException("A new insatEvent cannot already have an ID", ENTITY_NAME, "idexists");
        }
        InsatEvent result = insatEventService.save(insatEvent);
        return ResponseEntity.created(new URI("/api/insat-events/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /insat-events : Updates an existing insatEvent.
     *
     * @param insatEvent the insatEvent to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated insatEvent,
     * or with status 400 (Bad Request) if the insatEvent is not valid,
     * or with status 500 (Internal Server Error) if the insatEvent couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/insat-events")
    @Timed
    public ResponseEntity<InsatEvent> updateInsatEvent(@RequestBody InsatEvent insatEvent) throws URISyntaxException {
        log.debug("REST request to update InsatEvent : {}", insatEvent);
        if (insatEvent.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        InsatEvent result = insatEventService.save(insatEvent);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, insatEvent.getId().toString()))
            .body(result);
    }

    /**
     * GET  /insat-events : get all the insatEvents.
     *
     * @param eagerload flag to eager load entities from relationships (This is applicable for many-to-many)
     * @return the ResponseEntity with status 200 (OK) and the list of insatEvents in body
     */
    @GetMapping("/insat-events")
    @Timed
    public List<InsatEvent> getAllInsatEvents(@RequestParam(required = false, defaultValue = "false") boolean eagerload) {
        log.debug("REST request to get all InsatEvents");
        return insatEventService.findAll();
    }

    /**
     * GET  /insat-events/:id : get the "id" insatEvent.
     *
     * @param id the id of the insatEvent to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the insatEvent, or with status 404 (Not Found)
     */
    @GetMapping("/insat-events/{id}")
    @Timed
    public ResponseEntity<InsatEvent> getInsatEvent(@PathVariable Long id) {
        log.debug("REST request to get InsatEvent : {}", id);
        Optional<InsatEvent> insatEvent = insatEventService.findOne(id);
        return ResponseUtil.wrapOrNotFound(insatEvent);
    }

    /**
     * DELETE  /insat-events/:id : delete the "id" insatEvent.
     *
     * @param id the id of the insatEvent to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/insat-events/{id}")
    @Timed
    public ResponseEntity<Void> deleteInsatEvent(@PathVariable Long id) {
        log.debug("REST request to delete InsatEvent : {}", id);
        insatEventService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * SEARCH  /_search/insat-events?query=:query : search for the insatEvent corresponding
     * to the query.
     *
     * @param query the query of the insatEvent search
     * @return the result of the search
     */
    @GetMapping("/_search/insat-events")
    @Timed
    public List<InsatEvent> searchInsatEvents(@RequestParam String query) {
        log.debug("REST request to search InsatEvents for query {}", query);
        return insatEventService.search(query);
    }

}