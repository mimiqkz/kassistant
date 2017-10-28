$(document).ready(function() {
    $('.alert-danger').hide();


});


$('ul.nav li.dropdown').hover(function() {
    $(this).find('.dropdown-menu-user').stop(true, true).delay(200).fadeIn(500);
}, function() {
    $(this).find('.dropdown-menu-user').stop(true, true).delay(200).fadeOut(500);
});


//Form Submit stuff
function sendData()
{
    $.ajax(
        {
            type: "POST",
            data: $("#registration-form").serialize(),
            cache: false,
            url: "/sign-up",
            success: function(data, textStatus, jqXHR)
            {
                $(".alert-danger-signup").hide();
                window.location.reload();
            },
            error: function(jqXHR)
            {
                $(".alert-danger-signup").show();
                $(".error-message-signup").text(jqXHR.responseText);
            }

        });
}

function sendLogin()
{
    $.ajax(
        {
            type: "POST",
            data: $("#login-form").serialize(),
            cache: false,
            url: "/login",
            success: function(data, textStatus, jqXHR)
            {
                $(".alert-danger-login").hide();
                window.location.reload();
            },
            error: function(jqXHR)
            {
                $(".alert-danger-login").show();
                $(".error-message-login").text("Username or password incorrect");
            }

        });


}