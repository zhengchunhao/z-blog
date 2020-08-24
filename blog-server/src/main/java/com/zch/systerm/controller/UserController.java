package com.zch.systerm.controller;

import com.zch.common.TablePageResult;
import com.zch.systerm.entity.User;
import com.zch.systerm.service.UserService;
import com.zch.utils.MyResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController extends BaseController{
   @Autowired
   private UserService userService;

   @GetMapping("/users")
   public TablePageResult userList( User user){
       pageIng();
       return  tablePageResult(userService.queryAll(user));
   }

   @PostMapping("/user")
    public MyResponse addUser(@RequestBody User user){
       return  result(userService.insert(user));
   }

   @PutMapping("/user")
   public  MyResponse updateUser(@RequestBody User user){
       return  result(userService.update(user));
   }

  @GetMapping("/user/{id}")
    public  MyResponse queryUserBtId(@PathVariable("id") Long id){
       User user=userService.queryById(id);
      return  MyResponse.success(user);
  }

  @GetMapping("/userNameUnique")
   public  boolean  CheckUserNameUnique(@RequestParam("userName") String userName,@RequestParam(value = "id",required = false) Long id){
     if(id!=null){
        if(userService.checkUserNameUnique(userName)){
            return  true;
         } else {
           User user=userService.queryById(id);
           if(user.getUsername().equals(userName)){
               return  true;
           }else {
               return  false;
           }
        }
     } else {
         return  userService.checkUserNameUnique(userName);
     }

  }
  @GetMapping("/phoneUnique")
    public  boolean  CheckPhoneUnique(@RequestParam("phone") String phone,@RequestParam(value = "id",required = false) Long id){
       if(id!=null){
       if(userService.checkPhoneUnique(phone)){
       return  true;
       }else {
        User user=userService.queryById(id);
        if(user.getPhone()!=null&&user.getPhone().equals(phone)){
            return  true;
        }else {
            return  false;
        }
       }
       }else {
       return  userService.checkPhoneUnique(phone);
       }

    }

    @GetMapping("/emailUnique")
    public  boolean  CheckEmailUnique(@RequestParam("email") String email,@RequestParam(value = "id" ,required = false) Long id){
       if(id!=null){
           if(userService.checkEmailUnique(email)){
               return  true;
           }else {
               User user=userService.queryById(id);
               if(user.getEmail()!=null&&user.getEmail().equals(email)){
                   return  true;
               }else {
                   return false;
               }
           }
       }else {
           return  userService.checkEmailUnique(email);
       }

    }

  @DeleteMapping("/users/{ids}")

  public  MyResponse deleteUsers(@PathVariable(value = "ids") Long[] ids){

     return result(userService.deleteById(ids));
  }


}
