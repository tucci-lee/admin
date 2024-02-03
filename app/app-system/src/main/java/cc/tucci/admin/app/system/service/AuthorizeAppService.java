package cc.tucci.admin.app.system.service;

import cc.tucci.admin.app.system.dto.body.PasswordUpdateBody;
import cc.tucci.admin.app.system.dto.body.ProfileUpdateBody;
import cc.tucci.admin.app.system.dto.body.SigninBody;
import cc.tucci.admin.app.system.exe.SigninExe;
import cc.tucci.admin.app.system.exe.SignoutExe;
import cc.tucci.admin.app.system.exe.UserPasswordUpdateExe;
import cc.tucci.admin.app.system.exe.UserProfileGetExe;
import cc.tucci.admin.app.system.exe.UserProfileUpdateExe;
import cc.tucci.admin.domain.core.dto.Response;
import org.springframework.stereotype.Service;

/**
 * @author tucci
 */
@Service
public class AuthorizeAppService {

    private final SigninExe signinExe;
    private final SignoutExe signoutExe;
    private final UserPasswordUpdateExe userPasswordUpdateExe;
    private final UserProfileGetExe userProfileGetExe;
    private final UserProfileUpdateExe userProfileUpdateExe;

    public AuthorizeAppService(SigninExe signinExe,
                               SignoutExe signoutExe,
                               UserPasswordUpdateExe userPasswordUpdateExe,
                               UserProfileGetExe userProfileGetExe,
                               UserProfileUpdateExe userProfileUpdateExe) {
        this.signinExe = signinExe;
        this.signoutExe = signoutExe;
        this.userPasswordUpdateExe = userPasswordUpdateExe;
        this.userProfileGetExe = userProfileGetExe;
        this.userProfileUpdateExe = userProfileUpdateExe;
    }

    public Response signin(SigninBody body) {
        return signinExe.execute(body);
    }

    public Response signout() {
        return signoutExe.execute();
    }

    public Response profile() {
        return userProfileGetExe.execute();
    }

    public Response updateProfile(ProfileUpdateBody body) {
        return userProfileUpdateExe.execute(body);
    }

    public Response updatePassword(PasswordUpdateBody body) {
        return userPasswordUpdateExe.execute(body);
    }
}
