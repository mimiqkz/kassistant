$(document).on('change', ':file', function() {
        var input = $(this),
        label = input.val().replace(/\\/g, '/').replace(/.*\//, '');
        input.trigger('fileselect', label);
        $('#file-output').val(label);
});