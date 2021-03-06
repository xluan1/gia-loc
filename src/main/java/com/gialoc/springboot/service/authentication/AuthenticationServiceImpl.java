package com.gialoc.springboot.service.authentication;

import com.gialoc.springboot.exception.ResourceNotFoundException;
import com.gialoc.springboot.jwt.JwtProvider;
import com.gialoc.springboot.model.User;
import com.gialoc.springboot.model.enums.RoleUser;
import com.gialoc.springboot.payload.request.LoginRequest;
import com.gialoc.springboot.payload.request.RegisterRequest;
import com.gialoc.springboot.repository.UserRepository;
import com.gialoc.springboot.service.base.BaseManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class AuthenticationServiceImpl extends BaseManager implements AuthenticationService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtProvider jwtProvider;

    /**
     * if email and password are valid then login success
     *
     * @param login
     **/
    @Override
    public Map<String, String> login(LoginRequest login) throws ResourceNotFoundException {
        try {
            User account = userRepository.findByEmail(login.getEmail());
            // check email and password
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(login.getEmail(), login.getPassword()));
            String token = jwtProvider.createToken(login.getEmail(), account.getRoleUser().toString());
            Map<String, String> response = new HashMap<>();
            response.put("email", login.getEmail());
            response.put("userName", account.getUserName());
            response.put("token", token);
            response.put("roleUser", account.getRoleUser().toString());
            return response;
        } catch (Exception e) {
            throw new ResourceNotFoundException("????ng nh???p kh??ng th??nh c??ng");
        }
    }

    /**
     * create new registration
     *
     * @param createUser
     **/
    private void createAccount(RegisterRequest createUser, RoleUser roleUser) throws ResourceNotFoundException {
        User resultData = userRepository.findByUserNameAndEmail(createUser.getEmail(), createUser.getUserName());
        if (resultData != null) {
            throw new ResourceNotFoundException("?????a ch??? email ho???c t??n ng?????i d??ng ???? t???n t???i!");
        }
        if (!createUser.getRePassword().equals(createUser.getPassword())) {
            throw new ResourceNotFoundException("M???t kh???u kh??ng gi???ng, y??u c???u nh???p l???i");
        }
        // create account
        User account = new User();
        account.setId(generateId());
        account.setCreatedAt(new Date());
        account.setEmail(createUser.getEmail().trim());
        account.setPassword(passwordEncoder.encode(createUser.getPassword()));
        account.setUserName(createUser.getUserName());
        account.setFirstName(createUser.getFirstName());
        account.setLastName(createUser.getLastName());
        account.setPhoneNumber(createUser.getPhoneNumber().trim());
        account.setCountry(createUser.getAddress().getCountry());
        account.setTown(createUser.getAddress().getTown());
        account.setProvince(createUser.getAddress().getProvince());
        account.setVillage(createUser.getAddress().getVillage());
        account.setHamlet(createUser.getAddress().getHamlet());
        account.setActive(true);
        account.setRoleUser(roleUser);
        userRepository.save(account);
    }

    /**
     * create new user
     *
     * @param createUser
     **/
    @Override
    public String registerUser(RegisterRequest createUser) throws ResourceNotFoundException {
        createAccount(createUser, RoleUser.USER);
        return "T???o t??i kho???n kh??ch h??ng th??nh c??ng";
    }

    /**
     * create new employee
     *
     * @param createUser
     **/
    @Override
    public String registerEmployee(RegisterRequest createUser) throws ResourceNotFoundException {
        createAccount(createUser, RoleUser.EMPLOYEE);
        return "T???o t??i kho???n nh??n vi??n th??nh c??ng";
    }

    /**
     * create new employee
     *
     * @param createUser
     **/
}
