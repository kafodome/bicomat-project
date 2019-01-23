package com.c353.projet.bicomat.resource;

import com.c353.projet.bicomat.data.Operation;
import com.c353.projet.bicomat.ejb.OperationBean;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author Kwami Anukana AFODOME
 */
@Stateless
@Path("/operation")
public class OperationService {

    public static final Logger logger
            = Logger.getLogger(OperationService.class.getCanonicalName());

    @Inject
    OperationBean operationBean;

    @GET
    @Path("all")
    @Produces({MediaType.APPLICATION_JSON})
    public List<Operation> getAllOperations() {

        List<Operation> operations = null;
        try {
            operations = this.operationBean.findAllOperations();
            if (operations == null) {
                throw new WebApplicationException(Response.Status.NOT_FOUND);
            }
        } catch (WebApplicationException ex) {
            logger.log(Level.SEVERE,
                    "Error calling findAllOperations()",
                    new Object[]{ex.getMessage()});
        }
        return operations;
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public Operation getOperation(@PathParam("id") Long numOperation) {
        Operation operation = null;

        try {
            operation = this.operationBean.findById(numOperation);
        } catch (Exception ex) {
            logger.log(Level.SEVERE,
                    "Error calling findOperation() for numOperation {0}. {1}",
                    new Object[]{numOperation, ex.getMessage()});
        }
        return operation;
    }

    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    public void createOperation(Operation operation) {

        try {
            this.operationBean.persist(operation);
        } catch (Exception e) {
            logger.log(Level.SEVERE,
                    "Error creating operation for numOperation {0}. {1}",
                    new Object[]{operation.getNumOperation(), e.getMessage()});
            throw new WebApplicationException(e,
                    Response.Status.INTERNAL_SERVER_ERROR);
        }
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_JSON})
    public Response updateOperation(@PathParam("id") Long numOperation,
            Operation operation) {

        try {
            Operation oldOperation = this.operationBean.findById(numOperation);

            if (oldOperation == null) {
                // return a not found in http/web format
                throw new WebApplicationException(Response.Status.NOT_FOUND);
            } else {
                this.operationBean.merge(operation);
                return Response.ok().status(303).build(); //return a seeOther code
            }
        } catch (WebApplicationException e) {
            throw new WebApplicationException(e,
                    Response.Status.INTERNAL_SERVER_ERROR);
        }
    }

    @DELETE
    @Path("{id}")
    public void deleteOperation(@PathParam("id") Long numOperation) {
        logger.log(Level.INFO, numOperation.toString());
        try {
            if (!this.operationBean.remove(numOperation)) {
                throw new WebApplicationException(Response.Status.NOT_FOUND);
            }
        } catch (Exception ex) {
            logger.log(Level.SEVERE,
                    "Error calling deleteOperation() for numOperation {0}. {1}",
                    new Object[]{numOperation, ex.getMessage()});
        }
    }

}
