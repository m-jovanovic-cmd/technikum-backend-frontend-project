package technikumbackendfrontendproject.Backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import technikumbackendfrontendproject.Backend.model.DTO.UserDTO;
import technikumbackendfrontendproject.Backend.model.User;
import technikumbackendfrontendproject.Backend.repository.UserRepository;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@Service
public class UserService  {

    Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
   }
    /*

    public User save(User user) {
        user.setStatus(String.valueOf(true));
        return userRepository.save(user);
    }
    */

    public User save(UserDTO userDTO) {
        System.out.println("saving user (service)");
        return userRepository.save(userDTO.convertToUser());
    }


    public String updateUser(User user) {
        //exist in the user object - yes - update, no - create/add
        boolean resourceFound = false;
        for(User currentUser: findAll()) {
            if(currentUser.getId() == user.getId()) {
                currentUser.setId(user.getId());
            }
        }
        if(!resourceFound){
            findAll().add(user);
            return "User Added Successfully";
        }
    return "User Updated Successfully";
    }

    public void deleteUser(Long id) {
        // Check if the user exists
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isPresent()) {
            userRepository.delete(userOptional.get());
        } else {

            // User not found, you can handle this as needed (e.g., throw an exception or return a status)
            throw new EntityNotFoundException("User with ID " + id + " not found");
        }
    }
    public void registerUser(User user) {
        user.setRole("Customer");
        user.setStatus("active");
        user.setAdmin(false);
        userRepository.save(user);
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User getUser(Long id) {
        var user = userRepository.findById(id);
        if (user.isEmpty()) {
            throw new EntityNotFoundException();
        }
        User user2 = user.get();
        return user2;
    }

    public Optional<User> findById(Long id) {
        return userRepository.findById(id);

    }

    public UserDTO updateUser(Long existingUserId, UserDTO updatedUserDto) {
        var existingUser = userRepository.findById(existingUserId);

        if (existingUser.isEmpty()) {
            throw new EntityNotFoundException("User with id: " + existingUserId + " does not exist!");
        } else {
            User updatedUser = existingUser.get();

            // Update the user information with values from the DTO
            logger.info("Updating USERNAME: " + updatedUser.getUsername() + " -> " + updatedUserDto.getUsername());
            updatedUser.setUsername(updatedUserDto.getUsername());
            logger.info("Updating STATUS: " + updatedUser.getStatus() + " -> " + updatedUserDto.getStatus());
            updatedUser.setStatus(updatedUserDto.getStatus());
            logger.info("Updating isAdmin: " + updatedUser.isAdmin() + " -> " + updatedUserDto.getIsAdmin());
            updatedUser.setAdmin(updatedUserDto.getIsAdmin());
            logger.info("Updating ROLE: " + updatedUser.getRole() + " -> " + updatedUserDto.getRole());
            updatedUser.setRole(String.valueOf(updatedUserDto.getRole()));
            logger.info("Updating EMAIL: " + updatedUser.getEmail() + " -> " + updatedUserDto.getEmail());
            updatedUser.setEmail(updatedUserDto.getEmail());
            logger.info("Updating GENDER: " + updatedUser.getGender() + " -> " + updatedUserDto.getGender());
            updatedUser.setGender(updatedUserDto.getGender());
            logger.info("Updating FIRSTNAME: " + updatedUser.getFirstname() + " -> " + updatedUserDto.getFirstname());
            updatedUser.setFirstname(updatedUserDto.getFirstname());
            logger.info("Updating LASTNAME: " + updatedUser.getLastname() + " -> " + updatedUserDto.getLastname());
            updatedUser.setLastname(updatedUserDto.getLastname());
            //logger.info("Updating USERNAME: " + updatedUser.getUsername() + " -> " + updatedUserDto.getUsername());
            updatedUser.setLocation(updatedUserDto.getLocation());
            //logger.info("Updating USERNAME: " + updatedUser.getUsername() + " -> " + updatedUserDto.getUsername());
            updatedUser.setPassword(updatedUserDto.getPassword());
            //logger.info("Updating USERNAME: " + updatedUser.getUsername() + " -> " + updatedUserDto.getUsername());
            updatedUser.setPostcode(updatedUserDto.getPostcode());
            //logger.info("Updating USERNAME: " + updatedUser.getUsername() + " -> " + updatedUserDto.getUsername());
            updatedUser.setStreet(updatedUserDto.getStreet());
            logger.info("Updating STEETNUMBER: " + updatedUser.getStreetnumber() + " -> " + updatedUserDto.getStreetnumber());
            updatedUser.setStreetnumber(updatedUserDto.getStreetnumber());

            // Save the updated user
            userRepository.save(updatedUser);
            return convertToUserDto(updatedUser);
        }
    }
    // Utility method to convert a User entity to UserDto
    public UserDTO convertToUserDto(User user) {
        UserDTO userDto = new UserDTO();
        userDto.setUsername(user.getUsername());
        userDto.setIsAdmin((user.isAdmin()));
        userDto.setStatus(user.getStatus());
        userDto.setRole(user.getRole());
        userDto.setEmail(user.getEmail());
        userDto.setGender(user.getGender());
        userDto.setFirstname(user.getFirstname());
        userDto.setLastname(user.getLastname());
        userDto.setLocation(user.getLocation());
        userDto.setPassword(user.getPassword());
        userDto.setPostcode(user.getPostcode());
        userDto.setStreet(user.getStreet());
        userDto.setStreetnumber(user.getStreetnumber());
        return userDto;
    }

}




