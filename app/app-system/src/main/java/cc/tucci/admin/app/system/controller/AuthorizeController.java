package cc.tucci.admin.app.system.controller;

import cc.tucci.admin.app.system.dto.body.PasswordUpdateBody;
import cc.tucci.admin.app.system.dto.body.ProfileUpdateBody;
import cc.tucci.admin.app.system.dto.body.SigninBody;
import cc.tucci.admin.app.system.service.AuthorizeAppService;
import cc.tucci.admin.domain.core.dto.Response;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;


/**
 * @author tucci
 */
@RestController
public class AuthorizeController {

    private final AuthorizeAppService authorizeAppService;

    public AuthorizeController(AuthorizeAppService authorizeAppService) {
        this.authorizeAppService = authorizeAppService;
    }

    /**
     * 登录
     */
    @PostMapping("signin")
    public Response signin(@Valid @RequestBody SigninBody body) {
        return authorizeAppService.signin(body);
    }

    /**
     * 退出登录
     */
    @PostMapping("signout")
    public Response signout() {
        return authorizeAppService.signout();
    }

    /**
     * 查询信息
     */
    @GetMapping("profile")
    public Response profile(){
        return authorizeAppService.profile();
    }

    /**
     * 修改信息
     */
    @PutMapping("profile")
    public Response updateProfile(@Valid @RequestBody ProfileUpdateBody body){
        return authorizeAppService.updateProfile(body);
    }

    /**
     * 修改密码
     */
    @PutMapping("password")
    public Response updatePassword(@Valid @RequestBody PasswordUpdateBody body) {
        return authorizeAppService.updatePassword(body);
    }
}
