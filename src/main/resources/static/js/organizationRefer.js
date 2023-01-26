/**
 * organization View
 */
window.addEventListener('DOMContentLoaded', event =>{

    //innerHTML ID
    const orgTree = document.querySelector('#orgTree');

    readAllOrgList();

    function readAllOrgList() {
        axios.get('/api/org/allList')
            .then(response => {
                updateOrgList(response.data)
            })
            .catch(err => {
                console.log(err)
            })
    }

    function updateOrgList(orgList) {
        let str = '';

        //현재 depart, team값
        let depart = '';
        let team = '';

        for (let l of orgList) {
            if (l.department != depart) {
                endDepartTag()
                str += '<li>'
                    + '<input type="checkbox" id="' + l.department + '"/>'
                    + '<label class="tree_label" for="' + l.department + '">' + l.department + '</label>'
                    + '<ul>'
                depart = l.department;
            }
            if (l.team != team) {
                endTeamTag()
                str +='<li>'
                    + '<input type="checkbox" id="' + l.team + '"/>'
                    + '<label for="' + l.team + '" class="tree_label">' + l.team + '</label>'
                    + '<ul>'
                team = l.team;
            }
            str += '<li>'
                // + '<input class="tree_label" type="text" data-id='+l.id+' value='+l.name +' '+ l.position +' readonly/></li>'
                + '<span class="tree_label" id="memberInfo" data-id="'+l.id+'">' + l.name +' '+ l.position + '</span></li>'

        }

        function endDepartTag(){
            if(depart != '') {
                endTeamTag()
                str +='</ul>'
                    + '</li>'
                team = '';
            }
        }

        function endTeamTag(){
            if(team != '') {
                str += '</ul>'
                    + '</li>'
            }
        }

        orgTree.innerHTML = str;


    }




});


