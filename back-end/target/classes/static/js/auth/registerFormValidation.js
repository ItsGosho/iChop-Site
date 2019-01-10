function runRegisterFormValidation() {

    const EMAIL_PATTERN = "";

     //Validate Email:
     let registerForm = $(`#emailRegisterForm`);

     registerForm.on(`keypress keyup`,emailValidator);

     function emailValidator(){
          let val = registerForm.val();
          let matcher = new RegExp("^([a-z0-9]{5,})$");
          console.log(val);
     }

     //Show/Hide Password:
    $(`#showPasswordRegisterForm`).on(`click`,showOrHidePassword);

    function showOrHidePassword() {
        if($(`#passwordRegisterForm`).attr(`type`)==='password'){
            $(`#passwordRegisterForm`).attr(`type`,`text`);
            $(`#confirmPasswordRegisterForm`).attr(`type`,`text`);

            $(`#showPasswordRegisterForm`).text(`Hide`);
        }else{
            $(`#passwordRegisterForm`).attr(`type`,`password`);
            $(`#confirmPasswordRegisterForm`).attr(`type`,`password`);
            $(`#showPasswordRegisterForm`).text(`Show`);
        }
    }
}