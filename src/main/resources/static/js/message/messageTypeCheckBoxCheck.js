function messageTypeCheckBoxCheck(element) {
    const checkBoxes = document.querySelectorAll('input[type=checkbox]');

    if(element.checked == true) {
        checkBoxes.forEach((cb) => {
            cb.checked = false;
        })
        element.checked = true;
    }
};