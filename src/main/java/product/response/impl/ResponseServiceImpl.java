package product.response.impl;

import product.response.ResponseService;
import product.response.model.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import product.response.model.*;
import product.response.model.Error;

import java.util.ArrayList;
import java.util.List;


@Component
public class ResponseServiceImpl implements ResponseService {

    @Value("${api.name}")
    private String server;

    @Override
    public ResponseEntity<Object> jsonValidationError(List<Object> errors, HttpStatus status) {
        JsonSchemaResponseError error = JsonSchemaResponseError.builder()
                .errors(errors)
                .code(status.value())
                .status(status.getReasonPhrase())
                .build();

        return new ResponseEntity<>(error, status);
    }

    @Override
    public ResponseEntity<Object> error(String error, HttpStatus status) {

        ResponseError responseError = ResponseError.builder()
                .message(error)
                .code(status.value())
                .status(status.getReasonPhrase())
                .build();

        return ResponseEntity.status(status.value()).body(responseError);
    }

    @Override
    public ResponseEntity<Object> success(Object data, HttpStatus status) {
        if(data instanceof Page){

            Page page = (Page) data;

            MetaPagination metaPagination = MetaPagination.builder()
                    .server(server)
                    .count(page.getTotalElements())
                    .limit(page.getSize())
                    .page(page.getNumber()+1)
                    .pageCount(page.getTotalPages())
                    .build();

            return new ResponseEntity<>(new ResponseSuccessPagination(metaPagination, page.getContent()), status);
        }


        return new ResponseEntity<>(new ResponseSuccess(new Meta(server), data), status);
    }

}
