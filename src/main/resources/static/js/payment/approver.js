window.addEventListener('DOMContentLoaded', event => {

    readAllApprover();

    // TODO: 병합 후 처리하기
    // 결재자 지정 창 ============================================================
    function readAllApprover() {
        axios.get('/api/payment/card/all')
            .then(response => {
                organic(response.data);
                approverList();
            })
            .catch(error => {
                console.log(error);
            });
    }

    // 조직도 그리기 =============================================================
    function organic(data) {

        const divOrganic = document.querySelector('#jb-text');

        let str1 = '';

        for (let r of data) {

            str1 +=

                `<div id="jb-text">
                      <fieldset class="border-0" style="height: 450px; border: 1px solid #bcbcbc">
                        <div class="container">
                          <ul class="tree" id="orgTree">
                            TODO: 조직도 나오기 전 test 용 
                            <li th:value="인사팀">인사팀</li>
                            <li th:value="개발팀">개발팀</li>
                            <li th:value="총무팀">총무팀</li>
                          </ul>
                        </div>
                      </fieldset>
                </div>`
        }
        divOrganic.innerHTML = str1;
    }

    // 결재자 지정 리스트
    function approverList() {

        const divApproverList = document.querySelector('#jb-sidebar');

        let str = '';

        for (let r of data) {

            str +=

                '<h2>안녕</h2>';

        }
        divApproverList.innerHTML = str;
    }

}) // window end