<template>
  <v-card>
    <v-data-table
      :headers="headers"
      :items="savetovanja"
      :sort-desc="[false, true]"
      multi-sort
      class="elevation-1"
    >
      <template v-slot:item.datum="{ item }">
        <span>{{ new Date(item.datum).toLocaleDateString("fr-CA") }}</span>
      </template>
    </v-data-table>
  </v-card>
</template>

<script>
export default {
  components: {},
  data: () => ({
    headers: [
      { text: "Savetovanje", value: "naziv", sortable: false },
      { text: "Datum", value: "datum" },
      { text: "Trajanje", value: "trajanje" },
    ],
    savetovanja: [],
    dialog: false,
    selectedUser: {},
    componentKey: 0,
  }),
  mounted() {
    this.loadSavetovanja();
  },
  methods: {
    async loadSavetovanja() {
      [
        {
          name: "Savetovanje 1",
          izvestaj: "",
          start: [2021, 4, 27, 16, 0],
          end: [2021, 4, 27, 17, 0],
          pacijentId: 2,
          id: 1,
          apotekaId: 1,
          farmaceutId: 1,
          izvrsen: false,
        },
        {
          name: "Savetovanje 7",
          izvestaj: "Primer izvrsenog savetovanaja 2",
          start: [2021, 4, 26, 12, 15],
          end: [2021, 4, 26, 13, 0],
          pacijentId: 2,
          id: 7,
          apotekaId: 1,
          farmaceutId: 1,
          izvrsen: true,
        },
        {
          name: "Savetovanje 11",
          izvestaj: "",
          start: [2021, 5, 25, 18, 0],
          end: [2021, 5, 25, 19, 0],
          pacijentId: 2,
          id: 11,
          apotekaId: 1,
          farmaceutId: 1,
          izvrsen: false,
        },
      ].forEach((element) => {
        this.savetovanja.push({
          naziv: element.name,
          datum: new Date(
            element.start[0].toString() +
              "-" +
              element.start[1].toString() +
              "-" +
              element.start[2].toString()
          ),
          trajanje:
            (element.end[3] * 60 + element.end[4]) -
            (element.start[3] * 60 + element.start[4]),
        });
      });
    },
  },
};
</script>