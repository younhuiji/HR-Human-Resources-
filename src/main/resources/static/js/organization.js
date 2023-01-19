/**
 * organization View
 */
window.addEventListener('DOMContentLoaded', event =>{
    console.log("org()!!!!")

    //checkbox 여부
    const team1_Level2 = document.querySelector('#team1_Level2');
    const team2_Level2 = document.querySelector('#team2_Level2');

    //innerhtml
    const team1 = document.querySelector('#team1');
    const team2 = document.querySelector('#team2');

    //team check
    const team1Input = document.querySelector('#team1Input')

    readAllOrgList();

    //일단 팀1만 load
    function readAllOrgList(){
        axios.get('/api/org/all/'+ team1Input.value)
            .then(response => {
                updateOrgList(response.data)
            })
            .catch(err => {
                console.log(err);
            })

    }

    function updateOrgList(data) {
        let str = '';
        for(let m of data){
            str += '<li><span class="tree_label">'+m.name +' '+ m.position+'</span></li>'
        }
        team1.innerHTML = str;

        console.log(str)
    }

    // const li = document.querySelectorAll('.tree_label li')
    // for(let i = 0; i<li.length; i++){
    //     li[i].addEventListener('click',(e) => {
    //         console.log(e.target.innerText);
    //     });
    // }
});


