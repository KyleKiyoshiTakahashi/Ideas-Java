package com.codingdojo.Ideas.services;

import java.util.List;
import java.util.Optional;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;

import com.codingdojo.Ideas.models.Idea;
import com.codingdojo.Ideas.models.User;
import com.codingdojo.Ideas.repositories.IdeaRepo;
import com.codingdojo.Ideas.repositories.LikeRepo;
import com.codingdojo.Ideas.repositories.UserRepo;

@Service
public class IdeaService {
	private final UserRepo userRepo;
	private final IdeaRepo ideaRepo;
	private final LikeRepo likeRepo;
	
	public IdeaService(UserRepo userRepo,  IdeaRepo ideaRepo, LikeRepo likeRepo) {
		this.userRepo = userRepo;
		this.ideaRepo = ideaRepo;
		this.likeRepo = likeRepo;
	}
	
	public User registerUser(User user) {
        String hashed = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
        user.setPassword(hashed);
        return userRepo.save(user);
    }
    public User findByEmail(String email) {
        return userRepo.findByEmail(email);
    }
    
    public User findUserById(Long id) {
    	Optional<User> u = userRepo.findById(id);
    	if(u.isPresent()) {
            return u.get();
    	}
    	else {
    	    return null;
    	}
    }
    
    public List<Idea> allIdeas(){
    	return ideaRepo.findAll();
    }
    
    public void deleteIdea(Long id) {
    	ideaRepo.deleteById(id);
    }
    
    public Idea addIdea(Idea idea) {
    	return ideaRepo.save(idea);
    }
    
    public Idea findIdeaById(Long id) {
    	Optional<Idea> i = ideaRepo.findById(id);
    	if(i.isPresent()) {
    		return i.get();
    	} else {
    		return null;
    	}
    }
    
    public void updateIdea(Idea idea) {
    	ideaRepo.save(idea);
    }
    
    public void updateUser(User user) {
    	userRepo.save(user);
    }
    
    public boolean authenticateUser(String email, String password) {
    	//  finds user by email  	
        User user = userRepo.findByEmail(email);
        // if we can't find the user by email, return false       
        if(user == null) {
        	System.out.println("authenticateUser page user == null " );
            return false;
            
        } else {
    	 // if the passwords match, return true, else, return false
        if(BCrypt.checkpw(password, user.getPassword())) {
        	System.out.println("authenticateUser page user password vs hashed pw");
            return true;
            
        }
        	else {
            return false;
        	}
        } 
    }
    //   checks if there is an email that already exists in the DB
    public boolean duplicateUser(String email) {
        User user = userRepo.findByEmail(email);
        if(user == null) {
            return false;
        }
        else {
        	return true;
        }
    }
}
