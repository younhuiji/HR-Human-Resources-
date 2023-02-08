window.addEventListener('DOMContentLoaded', event => {

    let query = window.location.search;
    let param = new URLSearchParams(query);
    let id = param.get('number');

    const div = document.querySelector('#title');
    const div2 = document.querySelector('#jb-header')

    if(id == 2){

        let str = `<h3 style="font-size: 30px"> 동반 출장자 지정</h3>`;
        div.innerHTML = str;

        let str2 = `결재내용의 동반 출장자를 지정합니다.`;
        div2.innerHTML = str2;

    } else{

        let str = `<h3 style="font-size: 30px">결재자 지정</h3>`;
        div.innerHTML = str;

        let str2 = `결재내용의 결재자를 지정합니다.`;
        div2.innerHTML = str2;

    }



});