window.addEventListener('DOMContentLoaded', event => {

    // list 카테고리 별로 변환하기
    const payment = document.querySelector('#payment');
    const form = document.querySelector('#form');
    payment.addEventListener('change', function (){
        form.action = "/payment/list";
        form.method = "get";
        form.submit();
    })

}) // window end