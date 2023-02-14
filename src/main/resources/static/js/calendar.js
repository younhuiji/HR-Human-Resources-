/**
 * calendar.js
 */

document.addEventListener('DOMContentLoaded', function() {
    //today
    var today = new Date();

    var year = today.getFullYear();
    var month = ('0' + (today.getMonth() + 1)).slice(-2);
    var day = ('0' + today.getDate()).slice(-2);

    var dateString = year + '-' + month  + '-' + day;
    console.log(dateString);

    //schedule Array
    let meetingList = [];
    let businessTripList = [];
    let vacationList = [];

    //modal
    const myModal = document.querySelector('#divModal');
    const calendarModal = new bootstrap.Modal(myModal);
    let modalText = document.querySelector('#modalBody');
    const modalTitle = document.querySelector('#ModalTitle');

    function showModal(list, groupId) {
        let str = '';
        if (groupId == 'meetingRoom') {
            str += '<p> 회의명: ' + list.title + '</p>'
                + '<p> 회의내용: '+ list.purpose +'</p>'
                + '<p>참석자: ' + list.attendee +'</p>'
                + '<p>회의시간:' + list.start + '~' + list.end + '</p>'
                + '<p>장소: ' + list.roomName + list.roomPlace+'</p>';
        } else if (groupId == 'businessTrip') {
            str += '<p>출장명: ' + list.title +'</p>'
                + '<p>출장내용: ' + list.reason +'</p>'
                + '<p>출장일자: ' + list.start + '~' + list.end +'</p>'
                + '<p>장소: ' + list.place + '</p>'
                + '<p>동반출장자 사번: ' + list.companionNo+'</p>'
        } else if (groupId == 'vacation'){
            str += '<p>휴가명: '+list.title+'</p>'
                + '<p>카테고리: '+list.category+'</p>'
                + '<p>휴가일자: '+list.start+'~'+list.end+'</p>'
                + '<p>사유: '+list.reason+'</p>'
        }

        modalText.innerHTML = str;
        modalTitle.innerHTML = groupId;
        calendarModal.show();
    }

    //loginUser
    const loginUser = document.querySelector('#loginUser').value;

    //calendar call
    var calendarEl = document.getElementById('calendar');

    var calendar = new FullCalendar.Calendar(calendarEl, {
        headerToolbar: {
            left: 'prev,next today',
            center: 'title',
            right: 'dayGridMonth,timeGridWeek,timeGridDay'
        },
        initialDate: dateString,
        navLinks: true, // can click day/week names to navigate views
        selectable: true,
        selectMirror: true,
        select: function(arg) {
            // var title = prompt('Event Title:');
            // if (title) {
            //     calendar.addEvent({
            //         title: title,
            //         start: arg.start,
            //         end: arg.end,
            //         allDay: arg.allDay
            //     })
            // }
            // calendar.unselect()
        },
        eventClick: function(arg) {
            // if (confirm('Are you sure you want to delete this event?')) {
            //     arg.event.remove()
            // }
            //calendarModal.show();
            if(arg.event.groupId == 'meetingRoom'){
                for(let l of meetingList){
                    if(l.meetingRoomNo == arg.event.id){
                        showModal(l, arg.event.groupId);
                        return;
                    }
                }
            }else if(arg.event.groupId == 'businessTrip'){
                for(let l of businessTripList){
                    if(l.businessNo == arg.event.id){
                        showModal(l, arg.event.groupId);
                        return;
                    }
                }
            }else if(arg.event.groupId =='vacation'){
                for(let l of vacationList){
                    if(l.vacationNo == arg.event.id){
                        showModal(l, arg.event.groupId);
                        return;
                    }
                }
            }


        },
        editable: true,
        dayMaxEvents: true, // allow "more" link when too many events
        eventSources: [{
            events: function (info, successCallback, failureCallback) {
                let events = [];
                axios.get('/api/org/meetingList/' + loginUser)
                    .then(response => {
                        console.log(response.data);
                        meetingList = response.data;
                        for (let l of response.data) {
                            events.push({
                                id: l.meetingRoomNo,
                                groupId: 'meetingRoom',
                                title: l.title,
                                start: l.reserveDate + 'T' + l.start,
                                end: l.reserveDate + 'T' + l.end
                            })
                        }
                        successCallback(events);
                    })
                    .catch(err => {
                        console.log(err);
                    })
            }
            , color: "#FF0000"
            , textColor: "#FFFFFF"
            },
            {
                events:function (info, successCallback, failureCallback) {
                    let events = []
                    axios.get('/api/org/businessTripList/' + loginUser)
                        .then(response => {
                            console.log(response.data);
                            businessTripList = response.data;
                            for(let l of response.data){
                                events.push({
                                    id: l.businessNo,
                                    groupId: 'businessTrip',
                                    title: l.title,
                                    start:l.start,
                                    end: l.end + 'T23:59:00'
                                })
                            }
                            successCallback(events);
                        })
                        .catch(err => {
                            console.log(err);
                        })
                }
                ,color : "#0000FF"
                ,textColor: "#FFFFFF"
            },
            {
                events:function (info, successCallback, failureCallback) {
                    let events = [];
                    axios.get('/api/org/vacationList/' + loginUser)
                        .then(response => {
                            console.log(response.data);
                            vacationList = response.data;
                            for(let l of response.data){
                                events.push({
                                    id:l.vacationNo,
                                    groupId:'vacation',
                                    title:l.title,
                                    start:l.start,
                                    end:l.end + 'T23:59:00'
                                })
                            }
                            successCallback(events);
                        })
                        .catch(err => {
                            console.log(err)
                        })
                }
                ,color : "#00CC00"
                ,textColor : "#FFFFFF"

            }

        ]


    });

    calendar.render();

});