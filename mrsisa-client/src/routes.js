import Vue from "vue";
import Router from "vue-router";

Vue.use(Router);

const router = new Router({
  mode: "history", // Use browser history
  routes: [
    {
      path: "/",
      name: "Prijava",
      component: () => import("./components/Prijava")
    },
    {
      path: "/promenaSifre",
      name: "ObaveznaPromenaSifre",
      component: () => import("./components/ObaveznaPromenaSifre")
    },
    {
      path: "/pacijent/aktiviraj/:id",
      name: "Aktivacija",
      component: () => import("./components/Aktivacija")
    },
    {
      path: "/prijava",
      name: "Prijava",
      component: () => import("./components/Prijava")
    },
    {
      path: "/registracija",
      name: "Registracija",
      component: () => import("./components/Registracija")
    },
    {
      path: "/pacijent",
      name: "HomepagePacijent",
      component: () => import("./components/HomepagePacijent")
    },
    {
      path: "/dobavljac",
      name: "HomepageDobavljac",
      component: () => import("./components/Dobavljac/HomepageDobavljac")
    },
    {
      path: "/registracija/zaposleni",
      name: "RegistracijaZaposlenog",
      component: () => import("./components/Admin/RegZaposlenog")
    },
    {
      path: "/apoteke/kreiraj",
      name: "RegApoteka",
      component: () => import("./components/Admin/RegApoteka")
    },
    {
      path: "/lekovi/kreiraj",
      name: "KreirajLek",
      component: () => import("./components/Admin/KreirajLek")
    },
    {
      path: "/lekovi/lek/:sifra",
      name: "Lek Details",
      component: () => import("./components/LekComponent")
    },
    {
      path: "/farmaceut",
      name: "HomepageDF",
      component: () => import("./components/HomepageDF")
    },
    {
      path: "/dermatolog",
      name: "HomepageDF",
      component: () => import("./components/HomepageDF")
    },
    {
      path: "/adminApoteke",
      name: "HomepageAdminApoteke",
      component: () => import("./components/HomepageAdminApoteke")
    },
    {
      path: "/admin",
      name: "HomepageAdmin",
      component: () => import("./components/Admin/HomepageAdmin")
    },
    {
      path: "/pretragaApoteka",
      name: "PretragaApoteka",
      component: () => import("./components/PretragaApoteka")
    },
    {
      path: "/pretragaLekova",
      name: "PretragaLekova",
      component: () => import("./components/PretragaLekova")
    },
  ]
});

export default router;