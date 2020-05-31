<?php
  $name = trim(strip_tags($_POST["name"]));
  $surname = trim(strip_tags($_POST["surname"]));
  $department = trim(strip_tags($_POST["department"]));
  $subjects = trim(strip_tags($_POST["subjects"]));
  $form = trim(strip_tags($_POST["form"]));
  $email = trim(strip_tags($_POST["email"]));
  $subject = "Registration on site http://localhost:63342/practice_10/Nova-Free-Theme-master/nova-free/index.html?_ijt=gpafacjbhkdi78kq0mumbtc4pn";
  $msg = "Your data:\n" ."Name: $name\n" ."Surname: $surname\n" ."Department: $department\n" ."Subjects: $subjects"."Form of training: $form\n"."E-mail: $email\n" ;
  $headers = "Content-type: text/plain; charset=UTF-8" . "\r\n";
  $headers .= "From: Elizabeth Shibut <ellis.shybut@gmail.com>" . "\r\n";
  $headers .= "Bcc: ellis.shybut@mail.ru". "\r\n";
  //if(!empty($name) && !empty($surname) && !empty($department) && !empty($subjects)&& !empty($form) && filter_var($email, FILTER_VALIDATE_EMAIL)){
    mail("ellis.shybut@mail.ru", $subject, $msg, $headers);
    echo "Thank you! You have successfully registered.";
   // }
   ini_set('display_errors','On');
   error_reporting('E_ALL');
?>