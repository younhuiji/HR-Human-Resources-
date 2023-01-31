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

    //loginUser
    const loginUser = document.querySelector('#loginUser');


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
            var title = prompt('Event Title:');
            if (title) {
                calendar.addEvent({
                    title: title,
                    start: arg.start,
                    end: arg.end,
                    allDay: arg.allDay
                })
            }
            calendar.unselect()
        },
        eventClick: function(arg) {
            // if (confirm('Are you sure you want to delete this event?')) {
            //     arg.event.remove()
            // }
            //calendarModal.show();
            for(let l of meetingList){
                if(l.meetingRoomNo == arg.event.id){
                    alert(
                        '회의명: '+ l.title +
                        '\n회의내용: '+l.purpose +
                        '\n참석자: '+l.attendee +
                        '\n회의시간:'+l.start+'~'+l.end +
                        '\n장소: '+l.roomName + l.roomPlace
                    )
                    return
                }
            }

        },
        editable: true,
        dayMaxEvents: true, // allow "more" link when too many events
        eventSources: [{
            events: function (info, successCallback, failureCallback) {
                let events = [];
                axios.get('/api/org/meetingList/'+loginUser)
                    .then(response => {
                        console.log(response.data);
                        meetingList = response.data;
                        for (let l of response.data) {
                            events.push({
                                id: l.meetingRoomNo,
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
            ,color: "#FF0000"
            ,textColor: "#FFFFFF"
        },
        {
            events:function (info, successCallback, failureCallback) {
                let events = []
                axios.get('/api/org/businessTripList')
                    .then(response => {
                        console.log(response.data);
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
                axios.get('/api/org/vacationList')
                    .then(response => {
                        console.log(response.data);
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