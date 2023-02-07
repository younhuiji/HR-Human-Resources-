window.addEventListener('DOMContentLoaded',function (){
    const form = document.querySelector('#form');
    const btn_findPw = document.querySelector('#btn_findPw');
    btn_findPw.addEventListener('click',function (){
        form.action = "/getPw";
        form.method = "post";
        form.submit();
    })
})