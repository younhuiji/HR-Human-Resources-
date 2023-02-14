  window.addEventListener('DOMContentLoaded', event => {
    // list 카테고리 별로 변환하기
    const payment = document.querySelector('#payment');

    const formPayment = document.querySelector('#form');

    payment.addEventListener('change', function (){
        formPayment.action = "/payment/list";
        formPayment.method = "get";
        formPayment.submit();
    })

    const formComplete = document.querySelector('#formComplete');
    payment.addEventListener('change', function (){
        formComplete.action = "/payment/complete";
        formComplete.method = "get";
        formComplete.submit();
    })

    const formProcess = document.querySelector('#formProcess');
    payment.addEventListener('change', function (){
        formProcess.action = "/payment/process";
        formProcess.method = "get";
        formProcess.submit();
    })

    const formResponse = document.querySelector('#formResponse');
    payment.addEventListener('change', function () {
        formResponse.action = "/payment/response";
        formResponse.method = "get";
        formResponse.submit();
    })

    const formRequest = document.querySelector('#formRequest');
    payment.addEventListener('change', function () {
        formRequest.action = "/payment/request";
        formRequest.method = "get";
        formRequest.submit();
    })

}) // window end