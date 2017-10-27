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
                $(".alert-danger").hide();
                window.location.reload();
            },
            error: function(jqXHR)
            {
                $(".alert-danger").show();
                $(".error-message").text(jqXHR.responseText);
            }

        });
}
