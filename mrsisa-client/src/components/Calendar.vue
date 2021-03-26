<template>
  <v-row class="fill-height">
    <v-col>
      <v-sheet height="64">
        <v-toolbar
          flat
        >
          <v-btn
            outlined
            class="mr-4"
            color="grey darken-2"
            @click="setToday"
          >
            Today
          </v-btn>
          <v-btn
            fab
            text
            small
            color="grey darken-2"
            @click="prev"
          >
            <v-icon small>
              mdi-chevron-left
            </v-icon>
          </v-btn>
          <v-btn
            fab
            text
            small
            color="grey darken-2"
            @click="next"
          >
            <v-icon small>
              mdi-chevron-right
            </v-icon>
          </v-btn>
          <v-toolbar-title v-if="$refs.calendar">
            {{ $refs.calendar.title }}
          </v-toolbar-title>
          <v-spacer></v-spacer>
          <v-menu
            bottom
            right
          >
            <template v-slot:activator="{ on, attrs }">
              <v-btn
                outlined
                color="grey darken-2"
                v-bind="attrs"
                v-on="on"
              >
                <span>{{ typeToLabel[type] }}</span>
                <v-icon right>
                  mdi-menu-down
                </v-icon>
              </v-btn>
            </template>
            <v-list>
              <v-list-item @click="type = 'day'">
                <v-list-item-title>Day</v-list-item-title>
              </v-list-item>
              <v-list-item @click="type = 'week'">
                <v-list-item-title>Week</v-list-item-title>
              </v-list-item>
              <v-list-item @click="type = 'month'">
                <v-list-item-title>Month</v-list-item-title>
              </v-list-item>
              <v-list-item @click="type = '4day'">
                <v-list-item-title>4 days</v-list-item-title>
              </v-list-item>
            </v-list>
          </v-menu>
        </v-toolbar>
      </v-sheet>
      <v-sheet height="600">
        <v-calendar
          ref="calendar"
          v-model="focus"
          color="primary"
          :events="events"
          :type="type"
          @click:event="showEvent"
          @click:more="viewDay"
          @click:date="viewDay"
          @change="updateRange"
        >
         <!--
          :first-interval= 8 
          :interval-count= 12
          -->
        <template v-slot:day-body="{ date, week }">
            <div
              class="v-current-time"
              :class="{ first: date === week[0].date }"
              :style="{ top: nowY }"
            ></div>
        </template>
        </v-calendar>
        <v-menu
          v-model="selectedOpen"
          :close-on-content-click="false"
          :activator="selectedElement"
          offset-x
        >
          <v-card
            color="grey lighten-4"
            min-width="350px"
            flat
          >
            <v-toolbar
              color="#1976D2"
              dark
            >
              <v-toolbar-title v-html="selectedEvent.name"></v-toolbar-title>
              <v-spacer></v-spacer>
              <v-btn icon @click="beginPregled">
                <v-icon>mdi-forward</v-icon>
              </v-btn>
              <v-btn icon @click="reportMiss">
                <v-icon>mdi-account-cancel</v-icon>
              </v-btn>
              <v-btn icon @click="cancelPregled">
                <v-icon>mdi-close-circle-outline</v-icon>
              </v-btn>
            </v-toolbar>
            <v-card-text>
              <div >Start: {{selectedEvent.start}}</div>
              <div >End: {{selectedEvent.end}}</div>
              <div >Pacijent: {{selectedEvent.pacijent}}
                  <v-btn icon @click="viewAccount">
                    <v-icon>mdi-account</v-icon>
                </v-btn>
              </div>
            </v-card-text>
            <v-card-actions>
              <v-btn
                text
                color="secondary"
                @click="selectedOpen = false"
              >
                Cancel
              </v-btn>
            </v-card-actions>
          </v-card>
        </v-menu>
      </v-sheet>
    </v-col>
  </v-row>
</template>

<script>
  export default {
      data: ()=>({
            focus: "",
            type: "week",
            typeToLabel: {
                month: "Month",
                week: "Week",
                day: "Day",
                "4day": "4 Days"
            },
            name: null,
            details: null,
            start: null,
            end: null,
            color: "#1976D2",
            currentlyEditing: null,
            selectedEvent: {},
            selectedElement: null,
            selectedOpen: false,
            events: [],
            dialog: false,
            ready: false,
      }),
      computed: {
        cal () {
            return this.ready ? this.$refs.calendar : null
        },
        nowY () {
            return this.cal ? this.cal.timeToY(this.cal.times.now) + 'px' : '-10px'
        },
        },
      mounted(){
          this.$refs.calendar.checkChange()
          this.ready = true
          this.scrollToTime()
          this.updateTime()
      },
      methods:{
          getEvents(){


          },
        viewDay ({ date }) {
            this.focus = date
            this.type = 'day'
        },
        getEventColor (event) {
            return event.color
        },
        setToday () {
            this.focus = ''
        },
        prev () {
            this.$refs.calendar.prev()
        },
        next () {
            this.$refs.calendar.next()
        },
        showEvent ({ nativeEvent, event }) {
            const open = () => {
            this.selectedEvent = event
            this.selectedElement = nativeEvent.target
            setTimeout(() => {
                this.selectedOpen = true
            }, 10)
            }

            if (this.selectedOpen) {
            this.selectedOpen = false
            setTimeout(open, 10)
            } else {
            open()
            }

            nativeEvent.stopPropagation()
        },
        updateRange () {
                    const events = []

                    var currentdate = new Date();

                    events.push({
                        name: "Pregled 1",
                        pacijent: "Pacijent 1",
                        start: new Date(currentdate.getTime()+900000*4),
                        end: new Date(currentdate.getTime()+900000*8),
                        timed: true,
                    })

                    events.push({
                        name: "Pregled 2",
                        pacijent: "Pacijent 2",
                        start: new Date(currentdate.getTime()+900000*10),
                        end: new Date(currentdate.getTime()+900000*16),
                        timed: true,
                    })

                    events.push({
                        name: "Pregled 3",
                        pacijent: "Pacijent 3",
                        start: new Date(currentdate.getTime()+900000*20),
                        end: new Date(currentdate.getTime()+900000*24),
                        timed: true,
                    })

                    this.events = events
      },
      rnd (a, b) {
        return Math.floor((b - a + 1) * Math.random()) + a
      },
      getCurrentTime () {
        return this.cal ? this.cal.times.now.hour * 60 + this.cal.times.now.minute : 0
      },
      scrollToTime () {
        const time = this.getCurrentTime()
        const first = Math.max(0, time - (time % 30) - 30)

        this.cal.scrollToTime(first)
      },
      updateTime () {
        setInterval(() => this.cal.updateTimes(), 60 * 1000)
      },
      viewAccount(){
          console.log(this.selectedEvent);
      },
      cancelPregled(){
          console.log(this.selectedEvent);
      },
      reportMiss(){
          console.log(this.selectedEvent);
      },
      beginPregled(){
          console.log(this.selectedEvent);
      },
      }
  }
</script>

<style lang="scss">
.v-current-time {
  height: 2px;
  background-color: #ea4335;
  position: absolute;
  left: -1px;
  right: 0;
  pointer-events: none;

  &.first::before {
    content: '';
    position: absolute;
    background-color: #ea4335;
    width: 12px;
    height: 12px;
    border-radius: 50%;
    margin-top: -5px;
    margin-left: -6.5px;
  }
}
</style>