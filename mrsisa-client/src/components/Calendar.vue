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
          :event-color="getEventColor"
          @click:event="showEvent"
          @click:more="viewDay"
          @click:date="viewDay"
          @change="farmaceut ? updateRangeFarmaceutAxios(): updateRangeDermatologAxios()"
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
            :color="$vuetify.theme.dark ? '#454545':'white'"
            min-width="350px"
            flat
          >
            <v-toolbar
              :color=" selectedEvent.pacijent ? selectedEvent.izvrsen ? 'grey': '#1976D2': 'green'"
              dark
            >
              <v-toolbar-title v-html="selectedEvent.name"></v-toolbar-title>
              <v-spacer></v-spacer>
              <v-btn icon @click="beginPregled" v-if="selectedEvent.pacijent && !selectedEvent.izvrsen" >
                <v-icon>mdi-forward</v-icon>
              </v-btn>
              <v-btn icon @click="reportMiss" v-if="selectedEvent.pacijent && !selectedEvent.izvrsen">
                <v-icon>mdi-account-cancel</v-icon>
              </v-btn>
              <v-btn icon @click="cancelPregled">
                <v-icon>mdi-close-circle-outline</v-icon>
              </v-btn>
            </v-toolbar>
            <v-card-text>
              <div >Start: {{selectedEvent.start}}</div>
              <div >End: {{selectedEvent.end}}</div>
              <div v-if="selectedEvent.pacijent">Pacijent: {{selectedEvent.pacijent}}
                  <v-btn icon @click="viewAccount">
                    <v-icon>mdi-account</v-icon>
                </v-btn>
              </div>
              <div v-if="selectedEvent.started">
                <v-textarea
                  v-model="selectedEvent.izvestaj"
                  outlined
                  name="izvestaj_input"
                  label="Izvestaj"
                  placeholder="Uneti izvestaj..."
                ></v-textarea>
                <v-row
                  align="center"
                  justify="space-around"
                >
                  <v-btn color='#1976D2' font-color='white' @click="openLekList">
                      <div style="color:white">Preporuči lek</div>
                  </v-btn>
                  <v-btn color='#1976D2' font-color='white' @click="endTermin">
                      <div style="color:white">Završi termin</div>
                  </v-btn>
                </v-row>
              </div>
              <div v-if="selectedEvent.izvrsen"> 
                <v-expansion-panels>
                <v-expansion-panel>
                  <v-expansion-panel-header>
                    Izveštaj
                  </v-expansion-panel-header>
                  <v-expansion-panel-content>
                    {{selectedEvent.izvestaj}}
                  </v-expansion-panel-content>
                </v-expansion-panel>
                </v-expansion-panels>
              </div>
            </v-card-text>
            <v-card-actions>
              <v-btn
                text
                :color="$vuetify.theme.dark ? 'white':'#404040'"
                @click="selectedOpen = false"
              >
                Cancel
              </v-btn>
            </v-card-actions>
          </v-card>
        </v-menu>
      </v-sheet>
    </v-col>
    <v-dialog
      v-model="dialog"
      persistent
      max-width="290"
    >
      <v-card>
        <v-card-title class="headline">
          Greška
        </v-card-title>
        <v-card-text>Nemoguće je izvršiti ovu akciju van termina.</v-card-text>
        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn
            color="green darken-1"
            text
            @click="endDialog"
          >
            Ok
          </v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>
    <v-dialog
    v-model="lekDialog"
    persistent
    scrollable
    retain-focus
      max-width="760">
      <v-card>
        <v-card-title class="headline">
          Lekovi
        </v-card-title>
        <ListLekoviV2 :apotekaId="selectedEvent.apoteka" :preporuceniLekovi="preporuceniLekovi" :key="componentKey"/>
        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn
            color="green darken-1"
            text
            @click="endListDialog"
          >
            Ok
          </v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>
    <v-dialog
    v-model="terminDialog"
    persistent
    scrollable
    retain-focus
      max-width="960">
      <v-card>
        <v-card-title class="headline">
          Termin je završen
        </v-card-title>
        <v-card-text>
        <TerminPicker @termin-end="endTerminDialog" :izvestaj="selectedEvent.izvestaj" :apotekaId="selectedEvent.apoteka" :pacijentId="selectedEvent.pacijent" :farmaceut="farmaceut" :doktorId="user.id" :key="componentKey"/>
        </v-card-text>
        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn
            color="green darken-1"
            text
            @click="endTerminDialog"
          >
            Ok
          </v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>
    <v-dialog
    v-model="pacijentDialog"
    persistent
    scrollable
    retain-focus
      max-width="960">
      <v-card>
        <v-card-title class="headline">
          Nalog pacijenta
        </v-card-title>
        <v-card-text>
        <AccountView :user="selectedPacijent" :farmaceut="farmaceut" :editable="false" :key="componentKey"/>
        </v-card-text>
        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn
            color="green darken-1"
            text
            @click="endPacijentDialog"
          >
            Ok
          </v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>
  </v-row>
</template>

<script>
  import axios from "axios";
  import ListLekoviV2 from "./ListLekoviV2"
  import TerminPicker from "./TerminPicker"
  import AccountView from "./AccountView";

  export default {
    components:{
      ListLekoviV2,
      TerminPicker,
      AccountView,
    },
      data: ()=>({
            focus: "",
            type: "month",
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
            selectedPacijent: {},
            events: [],
            dialog: false,
            lekDialog: false,
            terminDialog: false,
            pacijentDialog: false,
            ready: false,
            preporuceniLekovi: [],
            componentKey:0,
      }),
      props: {
        farmaceut: Boolean,
        user: {},
      },
      computed: {
        cal () {
            return this.ready ? this.$refs.calendar : null
        },
        nowY () {
            return this.cal ? this.cal.timeToY(this.cal.times.now) + 'px' : '-10px'
        },
        },
      mounted(){
          console.log(this.user);
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
            } 
            else {
            open()
            }

            nativeEvent.stopPropagation()
        },
        async updateRangeDermatologAxios(){
          await axios.get(`http://localhost:8080/pregled/all/${this.user.id}`).then(response => {
           const events = []
          response.data.forEach(element => {
            events.push({
              name:element.name,
              pacijent:element.pacijentId,
              apoteka: element.apotekaId,
              start: new Date(element.start[0].toString()+"-"+element.start[1].toString()+"-"+element.start[2].toString()+" "+element.start[3].toString()+":"+element.start[4].toString()),
              end: new Date(element.end[0].toString()+"-"+element.end[1].toString()+"-"+element.end[2].toString()+" "+element.end[3].toString()+":"+element.end[4].toString()),
              izvrsen: element.izvrsen,
              izvestaj: element.izvestaj,
              id: element.id,
              started: false,
              timed: true,
            })
            this.events = events
          });
        });
        },
      async updateRangeFarmaceutAxios (){
        await axios.get(`http://localhost:8080/savetovanje/all/${this.user.id}`).then(response => {
           const events = []
          response.data.forEach(element => {
            events.push({
              name:element.name,
              pacijent:element.pacijentId,
              apoteka: element.apotekaId,
              start: new Date(element.start[0].toString()+"-"+element.start[1].toString()+"-"+element.start[2].toString()+" "+element.start[3].toString()+":"+element.start[4].toString()),
              end: new Date(element.end[0].toString()+"-"+element.end[1].toString()+"-"+element.end[2].toString()+" "+element.end[3].toString()+":"+element.end[4].toString()),
              izvrsen: element.izvrsen,
              izvestaj: element.izvestaj,
              id: element.id,
              started: false,
              timed: true,
            })
            this.events = events
          });
        });
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
      async viewAccount(){
          await axios.get(`http://localhost:8080/pacijent/${this.selectedEvent.pacijent}`).then(response=>{
            this.selectedPacijent=response.data
          })
          this.componentKey+=1;
          this.pacijentDialog= true
          this.selectedOpen=false
      },
      cancelPregled(){
          console.log(this.selectedEvent);
      },
      async reportMiss(){
          let now = new Date();
          if(now.getTime()<this.selectedEvent.start.getTime() && now.getTime()>this.selectedEvent.end.getTime()){
            this.dialog=true
          }else{
            let path="pregled"
            if(this.farmaceut){
              path="savetovanje"
            }
            await axios.put(`http://localhost:8080/${path}/penal/${this.selectedEvent.id}`);
            await axios.put(`http://localhost:8080/pacijent/penal/${this.selectedEvent.pacijent}`);
            //Object.assign(this.$data, this.$options.data.apply(this))
            this.selectedOpen=false
            this.farmaceut ?  this.updateRangeFarmaceutAxios():  this.updateRangeDermatologAxios()
            
          }   
      },
      beginPregled(){
          let now = new Date();
          this.preporuceniLekovi=[]
          if(now.getTime()<this.selectedEvent.start.getTime() && now.getTime()>this.selectedEvent.end.getTime()){
            this.dialog=true
          }else{
            this.selectedEvent.started=true;
          }
      },
      getEventColor (event) {
        if(event.pacijent){
          if(event.izvrsen){
              return 'grey';
          } else{
              return '#1976D2';
          }
        }else{
          return 'green';
        }
      },
      async endTermin(){
        let path="pregled"
        if(this.farmaceut){
          path="savetovanje"
        }
        console.log(path);
        let returnLekovi=[]
        this.preporuceniLekovi.forEach(element => {
          returnLekovi.push({
            terapija:element.terapija,
            lekId: element.lek.id
          })
        });
        await axios.put(`http://localhost:8080/${path}/izvestaj/${this.selectedEvent.id}`,{text:this.selectedEvent.izvestaj,lekovi:returnLekovi});
        this.componentKey+=1;
        this.terminDialog=true
        this.selectedOpen=false
      },
      endDialog(){
        this.dialog=false;
      },
      endPacijentDialog(){
        this.pacijentDialog= false
        this.selectedOpen=true
      },
      openLekList(){
        this.componentKey+=1;
        this.selectedOpen=false
        this.lekDialog=true;
      },
      endListDialog(){
        this.lekDialog=false;
        setTimeout(() => {
          
        }, 1000);

        this.selectedOpen=true
      },
      endTerminDialog(){
        this.terminDialog=false;
        this.selectedElement=null
        this.selectedEvent={}
        //Object.assign(this.$data, this.$options.data.apply(this))
        this.farmaceut ?  this.updateRangeFarmaceutAxios():  this.updateRangeDermatologAxios()
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