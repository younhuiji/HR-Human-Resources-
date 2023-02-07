window.addEventListener('DOMContentLoaded',function (){
    const form = document.querySelector('#form');
    const btn_setPw = document.querySelector('#btn_setPw');
    btn_setPw.addEventListener('click',function (){
        form.action = "/setPw";
        form.method = "post";
        form.submit();
    })
})