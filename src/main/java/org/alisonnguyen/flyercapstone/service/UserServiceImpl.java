package org.alisonnguyen.flyercapstone.service;

import lombok.extern.slf4j.Slf4j;
import org.alisonnguyen.flyercapstone.configuration.UserPrincipal;
import org.alisonnguyen.flyercapstone.controller.dto.UserDTO;
import org.alisonnguyen.flyercapstone.model.Calendar;
import org.alisonnguyen.flyercapstone.model.Role;
import org.alisonnguyen.flyercapstone.model.User;
;
import org.alisonnguyen.flyercapstone.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

/**
 * @implNote UserPrincipal class which implements UserDetails interface.
 * This way you get more flexibility and control over user authorization and authentication process.
 */
@Service
@Slf4j
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleService roleService;

    @Autowired
    private CalendarService calendarService;

    @Autowired
    private BCryptPasswordEncoder encoder;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        User user = userRepository.findUserByUserName(userName);
        log.debug(userName);
        if (user == null) {
            log.warn("Invalid username or password {}", userName);
            throw new UsernameNotFoundException("Invalid username or password.");
        }
/* return new org.springframework.security.core.userdetails.User(user.getUserName(),
user.getPassword(), mapRolesToAuthorities(user.getRoles()));*/
        return new UserPrincipal(user, roleService.getRolesByUser(user.getId()));
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
        return roles.stream().map(role -> new
                SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }

    /**
     * Using model mapper helps to avoid extra coding
     *
     * @param userDTO
     */
    @Transactional
    public void create(UserDTO userDTO) {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        User user = modelMapper.map(userDTO, User.class);

        Calendar calendar = new Calendar("Default");
        calendarService.saveCalendar(calendar);
//        calendar.setUser(user);
        user.setPassword(encoder.encode(user.getPassword()));

        user.setRoles(Arrays.asList(roleService.findRoleByRoleName("ROLE_USER")));
//        user.setCalendars(((calendarService.getAllCalendars())));
        userRepository.save(user);
    }

    /**
     * In this example login and email has the same values @param email @return
     */
    public User findUserByEmail(String email) {
        return userRepository.findUserByEmail(email);
    }

    public User findUserByName(String name) {
        return userRepository.findUserByUserName(name);
    }
}