window.addEventListener('DOMContentLoaded', event => {

    // alert('넘어가짐?');
    //
    // readAll();
    //
    // function readAll() {
    //     const employeeNo = document.querySelector('#employeeNo').value;
    //
    //     axios.get('/api/approver/readAll/' + 1)
    //         .then(response => {
    //             alert('넘겨짐');
    //             updateList(response.data);
    //         })
    //         .catch(err => {
    //             console.log(err);
    //         })
    // }

    // function updateList(data) {
    //     const div = document.querySelectorAll('#jb-sidebar');
    //
    //     let str = '';
    //
    //     for(let r of data) {
    //         str +=
    //             `<legend class="title">퇴사 신청 리스트</legend>
    //             <br>
    //             <form>
    //                 <div class="row" style="padding: 10px">
    //                     <div class="col-10">
    //                         <input type="text" class="form-control"
    //                                name="keyword" placeholder="검색어를 입력하세요" required autofocus />
    //                     </div>
    //                     <div class="col-2">
    //                         <input type="submit" class="btn btn-light" value="찾기" />
    //                     </div>
    //                 </div>
    //             </form>
    //             <form  >
    //                 <fieldset class="border-0">
    //
    //                     <table class="table" style="border-collapse: collapse">
    //                         <thead>
    //                         <tr>
    //                             <th>번호</th>
    //                             <th>제목</th>
    //                             <th>작성 시간</th>
    //                             <th>결재 상태</th>
    //                         </tr>
    //                         </thead>
    //                         <tbody>
    //                         <tr th:each="l : ${leave}" th:onclick="|location.href='@{/leave/detail(no=${l.no})}'|">
    //                             <td th:text="${l.no}"></td>
    //                             <td th:text="${l.title}"></td>
    //                             <td th:text="${ #temporals.format(l.createdTime, 'yyyy년 MM월 dd일') }"></td>
    //                             <td th:text="${l.state}"></td>
    //                         </tr>
    //                         </tbody>
    //                     </table>
    //                 </fieldset>
    //             </form>`
    //     }
    //     div.innerHTML = str;
    // }

})