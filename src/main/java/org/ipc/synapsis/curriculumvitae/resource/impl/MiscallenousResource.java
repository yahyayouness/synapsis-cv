package org.ipc.synapsis.curriculumvitae.resource.impl;

import io.swagger.annotations.*;
import org.ipc.synapsis.curriculumvitae.bean.exception.ResourceNotFound;
import org.ipc.synapsis.curriculumvitae.bean.in.AcademicBackgroundIn;
import org.ipc.synapsis.curriculumvitae.bean.in.MiscallenousIn;
import org.ipc.synapsis.curriculumvitae.bean.out.AcademicBackgroundOut;
import org.ipc.synapsis.curriculumvitae.bean.out.MiscallenousOut;
import org.ipc.synapsis.curriculumvitae.entity.CurriculumVitae;
import org.ipc.synapsis.curriculumvitae.resource.IAcademicBackgroundResource;
import org.ipc.synapsis.curriculumvitae.resource.IMiscallenousResource;
import org.ipc.synapsis.curriculumvitae.service.IAcademicBackgroundService;
import org.ipc.synapsis.curriculumvitae.service.IMiscallenousService;
import org.ipc.synapsis.curriculumvitae.util.constant.ParseExceptionConstant;
import org.ipc.synapsis.curriculumvitae.util.constant.ResourceExceptionConstant;
import org.ipc.synapsis.curriculumvitae.util.exception.ParseException;
import org.ipc.synapsis.curriculumvitae.util.exception.ResourceNotFoundException;
import org.ipc.synapsis.curriculumvitae.util.exception.http.HttpParseException;
import org.ipc.synapsis.curriculumvitae.util.exception.http.HttpResourceNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.UUID;

/**
 * Created by mbasri on 03/07/2016.
 */
@RestController
@RequestMapping(value = "/api/misc")
@Api(value = "Miscallenous", description = "'Miscallenous' resource base endpoint")
public class MiscallenousResource implements IMiscallenousResource {

    private static Logger LOGGER = LoggerFactory.getLogger(CurriculumVitaeResource.class);

    @Autowired
    IMiscallenousService miscallenousService;

    @Override
    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE )
    @ApiOperation(value = "Add a new 'Miscallenous'")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "New 'Miscallenous' successfully created", response = URI.class)
    })
    public ResponseEntity add(@ApiParam(value = "The 'Miscallenous' to add", required = true) @RequestBody MiscallenousIn miscallenousIn) {
        LOGGER.debug("Start call of the web service add new 'Miscallenous',{}",miscallenousIn);
        UUID id = miscallenousService.add(miscallenousIn);
        LOGGER.debug("End call of the web service add new 'Miscallenous',{}",miscallenousIn);
        return ResponseEntity.created(URI.create("/api/misc/"+id)).build();
    }

    @Override
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE )
    @ApiOperation(value = "Updates an existing 'Miscallenous'")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Existing 'Miscallenous' successfully updated", response = void.class)
    })
    public ResponseEntity update(@ApiParam(value = "The given 'Miscallenous' id", required = true) @PathVariable("id") String id,
                                 @ApiParam(value = "The 'Miscallenous' to update", required = true) @RequestBody MiscallenousIn miscallenousIn) {
        LOGGER.debug("Start call of the web service update 'Miscallenous',{}", miscallenousIn);
        miscallenousService.update(id, miscallenousIn);
        LOGGER.debug("End call of the web service update 'Miscallenous',{}", miscallenousIn);
        return ResponseEntity.ok().build();
    }

    @Override
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE )
    @ApiOperation(value = "Search a 'Miscallenous' by its id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "The 'Miscallenous' was found and is in the response", response = MiscallenousOut.class),
            @ApiResponse(code = 404, message = "The 'Miscallenous' cannot be found", response = ResourceNotFound.class)
    })
    public ResponseEntity get(@ApiParam(value = "The given 'Miscallenous' id", required = true) @PathVariable("id") String id) throws HttpResourceNotFoundException, HttpParseException {
        LOGGER.debug("Start call of the web service get 'Miscallenous' by id, id={}",id);
        MiscallenousOut miscallenousOut = null;
        try {
            miscallenousOut = miscallenousService.get(id);
        } catch (ResourceNotFoundException e) {
            LOGGER.warn("Resource 'Miscallenous' OUT not found, id:{}",id);
            throw  new HttpResourceNotFoundException(e.getResourceID(), e.getResourceName(),
                    ResourceExceptionConstant.MISCALLENOUS_NOT_FOUND_CODE, ResourceExceptionConstant.MISCALLENOUS_NOT_FOUND_VALUE);
        } catch (ParseException e) {
            LOGGER.error("Resource layer Cannot parse Sting to UUID");
            throw new HttpParseException(e.getSource(),e.getTarget(),
                    ParseExceptionConstant.PARSE_ERROR_STRING_UUID_CODE,ParseExceptionConstant.PARSE_ERROR_STRING_UUID_VALUE);
        }
        LOGGER.debug("End call of  the web service get 'Miscallenous' by id, id={}",id);
        return ResponseEntity.ok(miscallenousOut);
    }

    @Override
    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE )
    @ApiOperation(value = "Returns all existing 'Miscallenous'")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "All existing 'Miscallenous' are returned in a potentially empty list", response = Iterable.class)
    })
    public ResponseEntity getAll() {
        LOGGER.debug("Start call of the web service get all 'Miscallenous'");
        Iterable<MiscallenousOut> miscallenousOutList = miscallenousService.getAll();
        LOGGER.debug("End call of the web service get all 'Miscallenous'");
        return ResponseEntity.ok(miscallenousOutList);
    }

    @Override
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Deletes an existing 'Miscallenous'")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Existing 'Miscallenous' successfully deleted", response = void.class)
    })
    public ResponseEntity remove(@ApiParam(value = "The given 'Miscallenous' id", required = true) @PathVariable("id") String id) throws HttpParseException {
        LOGGER.debug("Start call of the web service delete 'Miscallenous' by id,id={}",id);
        try {
            miscallenousService.remove(id);
        }catch (ParseException e) {
            LOGGER.error("Resource layer Cannot parse Sting to UUID");
            throw new HttpParseException(e.getSource(),e.getTarget(),
                ParseExceptionConstant.PARSE_ERROR_STRING_UUID_CODE,ParseExceptionConstant.PARSE_ERROR_STRING_UUID_VALUE);
        }
            LOGGER.debug("End call of the web service delete 'Miscallenous' by id,id={}",id);
        return ResponseEntity.ok().build();
    }
}
