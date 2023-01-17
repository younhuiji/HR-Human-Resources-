window.addEventListener('DOMContentLoaded', function (){

    const employeeNo = document.querySelector('#employeeNo');

    employeeNo.addEventListener('change',function(){
        const employeeNoValue = employeeNo.value;
        console.log(employeeNoValue);
        axios
            .post('/checkNo',{
                No : employeeNoValue
            })
            .then(function (response) {
            console.log(response.data);
            })
            .catch(function (error) {
                console.log(error);
            });
    })


    
})