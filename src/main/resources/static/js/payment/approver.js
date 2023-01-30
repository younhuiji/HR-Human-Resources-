window.addEventListener('DOMContentLoaded', event => {

    readAllApprover();

    function readAllApprover() {
        axios.get('/api/approver/all')  // Ajax GET 요청 보냄.
            .then(response => {
                organic(response.data);
                approverList();
            })
            .catch(error => {
                console.log(error);
            });
    }

    // 조직도 그리기
    function organic(data) {

        const divOrganic = document.querySelector('#jb-text');

        let str1 = ''; // div 안에 들어갈 HTML 코드

        for (let r of data) {

            str1 +=
                `<h2>안녕</h2>`;

                `<!--<div id="jb-text">-->
<!--              <fieldset class="border-0" style="height: 450px; border: 1px solid #bcbcbc">-->
<!--                <div class="container">-->
<!--                  <ul class="tree" id="orgTree">-->
                    <!--TODO: 조직도 나오기 전 test 용 -->
<!--                    <li th:value="인사팀">인사팀</li>-->
<!--                    <li th:value="개발팀">개발팀</li>-->
<!--                    <li th:value="총무팀">총무팀</li>-->
<!--                  </ul>-->
<!--                </div>-->
<!--              </fieldset>-->

<!--          </div>-->`
        }
        divOrganic.innerHTML = str1;
    }

    // 결재자 지정 리스트
    function approverList() {

        const divApproverList = document.querySelector('#jb-sidebar');

        let str = ''; // div 안에 들어갈 HTML 코드

        for (let r of data) {

            str +=

                '<h2>안녕</h2>';

        }
        divApproverList.innerHTML = str;
    }

}) // window end