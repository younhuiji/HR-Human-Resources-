window.addEventListener('DOMContentLoaded', event => {

    // selectBox 로 create form 바꾸기
    const formType = document.addEventListener("change", selectNum);

    function selectNum() {
        const selectBox = document.getElementById('selectBox');

        const select = selectBox.options[selectBox.selectedIndex].value;

        if(select == 'leave'){
            location.href = 'http://localhost:8889/leave/create';
        } else if(select == 'vacation'){
            location.href = 'http://localhost:8889/payment/create';
        } else if(select == 'card'){
            location.href = 'http://localhost:8889/businessCard/create';
        } else if(select == 'trip'){
            location.href = 'http://localhost:8889/businessTrip/create';
        }
    }



}) // window end