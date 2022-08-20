

function Validator(options) {

    function validate(inputElement, rule){
        var errorMessage = rule.test(inputElement.value);
        var errorElement = inputElement.parentElement.querySelector(options.errorSelector);

        if(errorMessage){
            errorElement.innerText = errorMessage;
            inputElement.parentElement.classList.add('invalid');
        }else{
            errorElement.innerText = '';
            inputElement.parentElement.classList.remove('invalid');
        }
    }

    var formElement = document.querySelector(options.form);
    if (formElement){

        options.rules.forEach(function (rule) {

            var inputElement = formElement.querySelector(rule.selector);
            if (inputElement){
                inputElement.onblur = function () {
                    // value: inputElement.value
                    //test func : rule.test
                    validate(inputElement, rule);
                }
                
                inputElement.oninput = function () {
                    var errorElement = inputElement.parentElement.querySelector(options.errorSelector);
                    errorElement.innerText = '';
                    inputElement.parentElement.classList.remove('invalid');
                }
            }
        });
    }
}


Validator.isRequired = function (selector) {

    return {
        selector: selector,
        test: function (value) {
        return value ? undefined : 'Vui lòng nhập trường này!'
        }
    };
}

Validator.isEmail = function (selector) {

    return {
        selector: selector,
        test: function (value) {
            var regex = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;
            return regex.test(value) ? undefined : 'Email không hợp lệ!'
        }
    }
}