<template>
  <v-card>
    <v-data-table
      :headers="headers"
      :items="pregledi"
      :sort-desc="[false, true]"
      multi-sort
      class="elevation-1"
    >
      <template v-slot:item.datum="{ item }">
        <span>{{ new Date(item.datum).toLocaleDateString('fr-CA')}}</span>
      </template>
    </v-data-table>
  </v-card>
</template>

<script>
export default {
  components: {},
  data: () => ({
    headers: [
      { text: "Pregled", value: "naziv", sortable: false },
      { text: "Datum", value: "datum" },
      { text: "Cena", value: "cena" },
      { text: "Trajanje", value: "trajanje" },
    ],
    pregledi: [],
    dialog: false,
    selectedUser: {},
    componentKey: 0,
  }),
  mounted() {
    this.loadPregledi();
  },
  methods: {
    async loadPregledi() {
      [
        {
          name: "Pregled 2",
          izvestaj: "",
          start: [2021, 4, 27, 12, 0],
          end: [2021, 4, 27, 13, 0],
          pacijentId: 2,
          apotekaId: 1,
          id: 2,
          dermatologId: 3,
          izvrsen: false,
          cena: 3000.0,
        },
        {
          name: "Pregled 8",
          izvestaj: "Primer izvrsenog pregleda 1",
          start: [2021, 4, 25, 12, 0],
          end: [2021, 4, 25, 12, 0],
          pacijentId: 2,
          apotekaId: 1,
          id: 8,
          dermatologId: 3,
          izvrsen: true,
          cena: 3000.0,
        },
        {
          name: "Pregled 12",
          izvestaj: "",
          start: [2021, 5, 14, 12, 0],
          end: [2021, 5, 14, 13, 0],
          pacijentId: 2,
          apotekaId: 1,
          id: 12,
          dermatologId: 3,
          izvrsen: false,
          cena: 3000.0,
        },
      ].forEach((element) => {
        this.pregledi.push({
          naziv: element.name,
          datum: new Date(
            element.start[0].toString() +
              "-" +
              element.start[1].toString() +
              "-" +
              element.start[2].toString()
          ),
          cena: element.cena,
          trajanje:
            element.end[3] * 60 +
            element.end[4] -
            (element.start[3] * 60 + element.start[4]),
        });
      });
    },
  },
};
</script>