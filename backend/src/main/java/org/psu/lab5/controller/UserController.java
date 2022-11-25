package org.psu.lab5.controller;

import java.io.IOException;
import javax.validation.constraints.Null;
import org.psu.lab5.model.User;
import org.psu.lab5.service.UserService;
import org.psu.lab5.service.BinfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.psu.lab5.model.BinFile;

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    BinfileService binfileService;

    @GetMapping("")
    public ResponseEntity<User> selfInfo() {
        return ResponseEntity.status(HttpStatus.OK)
                .body(userService.getByUsername(userService.authInfo().getUsername()));
    }

    @PostMapping("file")
    public ResponseEntity<Null> uploadAvatar(@RequestParam("file") MultipartFile request) throws IOException {
        final String username = userService.authInfo().getUsername();
        User user = userService.getByUsername(username);
        if (user.getFile() != null) {
            user.getFile().setData(request.getBytes());
            user.getFile().setMimeType(request.getContentType());
        } else {
            BinFile binfile = binfileService.addMultipart(request);
            user.setFile(binfile);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(null);
    }

    /*
     * @GetMapping("avatar")
     * public ResponseEntity<Resource> downloadAvatar() {
     * Resource resource =
     * userRepository.getByUsername(usernameFromAuth()).orElseThrow(null)
     * }
     */

}
