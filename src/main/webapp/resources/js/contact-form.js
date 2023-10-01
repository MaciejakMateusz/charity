$(document).ready(function () {

    const form = $('#contact-form');
    const successMessage = $('#success-message');

    form.submit(function (event) {
        event.preventDefault();

        const formData = form.serialize(); // JSON

        $.ajax({
            type: 'POST',
            url: 'http://localhost:8080/contact-form',
            data: formData,
            success: function () {
                form.hide();
                successMessage.show();
            },
            error: function () {
                alert('Error submitting the form.');
            }
        });
    });
});