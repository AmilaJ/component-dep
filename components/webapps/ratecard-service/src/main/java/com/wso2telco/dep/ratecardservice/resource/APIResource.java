package com.wso2telco.dep.ratecardservice.resource;

import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.wso2telco.core.dbutils.exception.BusinessException;
import com.wso2telco.core.dbutils.exception.ServiceError;
import com.wso2telco.dep.ratecardservice.dao.model.APIDTO;
import com.wso2telco.dep.ratecardservice.dao.model.ErrorDTO;
import com.wso2telco.dep.ratecardservice.service.APIService;

@Path("/apis")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class APIResource {

	private final Log log = LogFactory.getLog(APIResource.class);
	private APIService apiService = new APIService();
	
	@GET
	public Response getAPIs() {

		List<APIDTO> apis = null;
		Status responseCode = null;
		Object responseString = null;

		try {

			apis = apiService.getAPIs();

			if (!apis.isEmpty()) {

				responseString = apis;
				responseCode = Response.Status.OK;
			} else {

				log.error("Error in APIResource getAPIs : apis are not found in database ");
				throw new BusinessException(ServiceError.NO_RESOURCES);
			}
		} catch (BusinessException e) {

			ErrorDTO errorDTO = new ErrorDTO();
			ErrorDTO.ServiceException serviceException = new ErrorDTO.ServiceException();

			serviceException.setMessageId(e.getErrorType().getCode());
			serviceException.setText(e.getErrorType().getMessage());
			errorDTO.setServiceException(serviceException);

			responseCode = Response.Status.NOT_FOUND;
			responseString = errorDTO;
		} catch (Exception e) {

			ErrorDTO errorDTO = new ErrorDTO();
			ErrorDTO.ServiceException serviceException = new ErrorDTO.ServiceException();

			if (e instanceof BusinessException) {

				BusinessException be = (BusinessException) e;
				serviceException.setMessageId(be.getErrorType().getCode());
				serviceException.setText(be.getErrorType().getMessage());
				errorDTO.setServiceException(serviceException);
			}

			responseCode = Response.Status.BAD_REQUEST;
			responseString = errorDTO;
		}

		log.debug("APIResource getAPIs -> response code : " + responseCode);
		log.debug("APIResource getAPIs -> response body : " + responseString);

		return Response.status(responseCode).entity(responseString).build();
	}
	
	@Path("/{apiName}/operations")
	public OperationResource getOperationResource() {

		return new OperationResource();
	}
}
