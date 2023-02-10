function sendPrevPage(str) {
    let referrer = document.referrer;

    let result = confirm(str);
    if(result) {
        location.href = referrer;
    }

}