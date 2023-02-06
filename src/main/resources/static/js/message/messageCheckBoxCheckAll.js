console.log("연결됨.");

function messageCheckBoxCheckAll(selectAll) {
    const checkboxes = document.getElementsByName('messageCheckBox');

    checkboxes.forEach((cb) => {
        cb.checked = selectAll.checked;
    })

}