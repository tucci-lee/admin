package cc.tucci.admin.app.core.handler;

import cc.tucci.admin.domain.core.dto.Response;
import cc.tucci.admin.domain.core.exception.BizCode;
import org.springframework.boot.autoconfigure.web.servlet.error.AbstractErrorController;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

/**
 * @author tucci
 */
@RestController
@RequestMapping("/error")
public class NotFoundException extends AbstractErrorController {


    public NotFoundException(ErrorAttributes errorAttributes) {
        super(errorAttributes);
    }

    @RequestMapping
    public Response error(HttpServletResponse response) {
        response.setStatus(HttpStatus.OK.value());
        return Response.fail(BizCode.NOT_FOUND.getCode(), BizCode.NOT_FOUND.getMessage());
    }

    @Override
    public String getErrorPath() {
        return null;
    }
}
