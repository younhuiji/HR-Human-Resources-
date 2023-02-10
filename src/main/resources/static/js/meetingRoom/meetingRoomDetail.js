window.addEventListener("DOMContentLoaded", (event) => {
    const form = document.querySelector("#formDetail");

    const deleteBtn = document.querySelector('#deleteBtn');
    deleteBtn.addEventListener('click', function() {

        const result = confirm('삭제하시겠습니까?')
            if(result) {
                form.action = '/meetingRoom/delete';
                form.method = 'post';
                form.submit();
            }
    });

    const updateBtn = document.querySelector('#updateBtn');
    updateBtn.addEventListener('click', function() {
        form.action = '/meetingRoom/modify';
        form.method = 'get';
        form.submit();
    });
});