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

    /**
     * Save a new user to the system by converting a UserDTO into a User object and persisting it.
     *
     * @param userDTO The UserDTO containing user information to be saved.
     * @return The User object that has been saved.
     */
    public User save(UserDTO userDTO) {

        // Convert the provided UserDTO into a User object and save it to the repository.
        return userRepository.save(userDTO.convertToUser());
    }


    /**
     * Deletes a user by their ID from the system, if the user exists.
     *
     * @param id The ID of the user to be deleted.
     * @throws EntityNotFoundException if the user with the specified ID doesn't exist.
     */
    public void deleteUser(Long id) {
        // Check if the user with the provided ID exists in the repository.
        Optional<User> userOptional = userRepository.findById(id);

        if (userOptional.isPresent()) {
            // If the user exists, delete them from the repository.
            userRepository.delete(userOptional.get());
        } else {
            // If the user is not found, throw an EntityNotFoundException.
            throw new EntityNotFoundException("User with ID " + id + " not found");
        }
    }

    /**
     * Registers a new user in the system with default role, status, and admin settings.
     *
     * @param user The User object to be registered.
     */
    public void registerUser(User user) {
        // Set default user attributes: role, status, and admin settings.
        user.setRole("Customer");
        user.setStatus("active");
        user.setAdmin(false);

        // Save the user to the repository.
        userRepository.save(user);
    }


    /**
     * Retrieve a list of all users from the repository.
     *
     * @return A list of User objects representing all users in the system.
     */
    public List<User> findAll() {
        return userRepository.findAll();
    }

    /**
     * Retrieve a user by their ID from the repository.
     *
     * @param id The ID of the user to retrieve.
     * @return The User object corresponding to the provided ID.
     * @throws EntityNotFoundException if the user with the specified ID doesn't exist.
     */
    public User getUser(Long id) {
        // Retrieve the user by their ID from the repository, or throw an EntityNotFoundException if not found.
        var user = userRepository.findById(id);
        if (user.isEmpty()) {
            throw new EntityNotFoundException();
        }
        User newUser = user.get();
        return newUser;
    }

    /**
     * Retrieve a user by their ID from the repository.
     *
     * @param id The ID of the user to retrieve.
     * @return An Optional containing the User object if found, or an empty Optional if not found.
     */
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }


    /**
     * Updates an existing user's information based on the provided UserDTO.
     *
     * @param existingUserId The ID of the existing user to be updated.
     * @param updatedUserDto The UserDTO containing the updated user information.
     * @return The UserDTO representing the updated user.
     * @throws EntityNotFoundException if the user with the specified ID doesn't exist.
     */
    public UserDTO updateUser(Long existingUserId, UserDTO updatedUserDto) {
        // Retrieve the existing user by their ID from the repository, if it exists.
        var existingUser = userRepository.findById(existingUserId);

        // Check if the user with the given ID exists, and if not, throw an EntityNotFoundException.
        if (existingUser.isEmpty()) {
            throw new EntityNotFoundException("User with id: " + existingUserId + " does not exist!");
        } else {
            User updatedUser = existingUser.get();

            // Update the user information with values from the DTO
            //logger.info("Updating USERNAME: " + updatedUser.getUsername() + " -> " + updatedUserDto.getUsername());
            updatedUser.setUsername(updatedUserDto.getUsername());
            //logger.info("Updating STATUS: " + updatedUser.getStatus() + " -> " + updatedUserDto.getStatus());
            updatedUser.setStatus(updatedUserDto.getStatus());
            //logger.info("Updating isAdmin: " + updatedUser.isAdmin() + " -> " + updatedUserDto.getIsAdmin());
            updatedUser.setAdmin(updatedUserDto.getIsAdmin());
            //logger.info("Updating ROLE: " + updatedUser.getRole() + " -> " + updatedUserDto.getRole());
            updatedUser.setRole(String.valueOf(updatedUserDto.getRole()));
            //logger.info("Updating EMAIL: " + updatedUser.getEmail() + " -> " + updatedUserDto.getEmail());
            updatedUser.setEmail(updatedUserDto.getEmail());
            //logger.info("Updating GENDER: " + updatedUser.getGender() + " -> " + updatedUserDto.getGender());
            updatedUser.setGender(updatedUserDto.getGender());
           // logger.info("Updating FIRSTNAME: " + updatedUser.getFirstname() + " -> " + updatedUserDto.getFirstname());
            updatedUser.setFirstname(updatedUserDto.getFirstname());
            //logger.info("Updating LASTNAME: " + updatedUser.getLastname() + " -> " + updatedUserDto.getLastname());
            updatedUser.setLastname(updatedUserDto.getLastname());
            //logger.info("Updating USERNAME: " + updatedUser.getUsername() + " -> " + updatedUserDto.getUsername());
            updatedUser.setLocation(updatedUserDto.getLocation());
            //logger.info("Updating USERNAME: " + updatedUser.getUsername() + " -> " + updatedUserDto.getUsername());
            updatedUser.setPassword(updatedUserDto.getPassword());
            //logger.info("Updating USERNAME: " + updatedUser.getUsername() + " -> " + updatedUserDto.getUsername());
            updatedUser.setPostcode(updatedUserDto.getPostcode());
            //logger.info("Updating USERNAME: " + updatedUser.getUsername() + " -> " + updatedUserDto.getUsername());
            updatedUser.setStreet(updatedUserDto.getStreet());
           // logger.info("Updating STREETNUMBER: " + updatedUser.getStreetnumber() + " -> " + updatedUserDto.getStreetnumber());
            updatedUser.setStreetnumber(updatedUserDto.getStreetnumber());

            // Save the updated user
            userRepository.save(updatedUser);

            // Convert the updated user to a UserDTO and return it.
            return convertToUserDto(updatedUser);
        }
    }
    /**
     * Converts a User object to a UserDTO, mapping user attributes to the corresponding fields in the DTO.
     *
     * @param user The User object to be converted.
     * @return A UserDTO representing the user's information.
     */
    public UserDTO convertToUserDto(User user) {
        // Create a new UserDTO to store the mapped user attributes.
        UserDTO userDto = new UserDTO();

        // Map the user attributes to the corresponding fields in the UserDTO.
        userDto.setUsername(user.getUsername());
        userDto.setIsAdmin(user.isAdmin());
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

        // Return the UserDTO containing the user's information.
        return userDto;
    }

}