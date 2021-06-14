(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-1de4c64c"],{"4bd4":function(t,e,r){"use strict";var a=r("5530"),i=(r("caad"),r("2532"),r("07ac"),r("4de4"),r("159b"),r("7db0"),r("58df")),n=r("7e2b"),o=r("3206");e["a"]=Object(i["a"])(n["a"],Object(o["b"])("form")).extend({name:"v-form",provide:function(){return{form:this}},inheritAttrs:!1,props:{disabled:Boolean,lazyValidation:Boolean,readonly:Boolean,value:Boolean},data:function(){return{inputs:[],watchers:[],errorBag:{}}},watch:{errorBag:{handler:function(t){var e=Object.values(t).includes(!0);this.$emit("input",!e)},deep:!0,immediate:!0}},methods:{watchInput:function(t){var e=this,r=function(t){return t.$watch("hasError",(function(r){e.$set(e.errorBag,t._uid,r)}),{immediate:!0})},a={_uid:t._uid,valid:function(){},shouldValidate:function(){}};return this.lazyValidation?a.shouldValidate=t.$watch("shouldValidate",(function(i){i&&(e.errorBag.hasOwnProperty(t._uid)||(a.valid=r(t)))})):a.valid=r(t),a},validate:function(){return 0===this.inputs.filter((function(t){return!t.validate(!0)})).length},reset:function(){this.inputs.forEach((function(t){return t.reset()})),this.resetErrorBag()},resetErrorBag:function(){var t=this;this.lazyValidation&&setTimeout((function(){t.errorBag={}}),0)},resetValidation:function(){this.inputs.forEach((function(t){return t.resetValidation()})),this.resetErrorBag()},register:function(t){this.inputs.push(t),this.watchers.push(this.watchInput(t))},unregister:function(t){var e=this.inputs.find((function(e){return e._uid===t._uid}));if(e){var r=this.watchers.find((function(t){return t._uid===e._uid}));r&&(r.valid(),r.shouldValidate()),this.watchers=this.watchers.filter((function(t){return t._uid!==e._uid})),this.inputs=this.inputs.filter((function(t){return t._uid!==e._uid})),this.$delete(this.errorBag,e._uid)}}},render:function(t){var e=this;return t("form",{staticClass:"v-form",attrs:Object(a["a"])({novalidate:!0},this.attrs$),on:{submit:function(t){return e.$emit("submit",t)}}},this.$slots.default)}})},"962f":function(t,e,r){"use strict";r.r(e);var a=function(){var t=this,e=t.$createElement,r=t._self._c||e;return r("v-card",{staticClass:"mt-16 mx-auto",attrs:{"min-width":"33%"}},[r("v-card-title",{staticClass:"justify-center"},[t._v("Prijava")]),r("v-card-text",[r("v-form",{ref:"forma",attrs:{"lazy-validation":""},model:{value:t.valid,callback:function(e){t.valid=e},expression:"valid"}},[r("v-text-field",{attrs:{rules:[t.rules.required,t.rules.email],label:"Email",required:""},model:{value:t.email,callback:function(e){t.email=e},expression:"email"}}),r("v-text-field",{attrs:{"append-icon":t.show?"mdi-eye":"mdi-eye-off",rules:[t.rules.required],type:t.show?"text":"password",label:"Lozinka"},on:{"click:append":function(e){t.show=!t.show}},model:{value:t.lozinka,callback:function(e){t.lozinka=e},expression:"lozinka"}})],1)],1),r("v-card-actions",[r("v-btn",{staticClass:"blue white--text",on:{click:t.cancel}},[t._v("Odustani")]),r("v-spacer"),r("a",{staticClass:"text-decoration-none",on:{click:t.register}},[t._v("Kreiranje naloga")]),r("v-spacer"),r("v-btn",{staticClass:"blue white--text",attrs:{disabled:!t.valid},on:{click:t.onSubmit}},[t._v("Prijava")])],1),r("v-card-actions",[r("v-btn",{on:{click:t.pretragaApoteka}},[t._v("Pretraga apoteka")]),r("v-spacer"),r("v-btn",{on:{click:t.pretragaLekova}},[t._v("Pretraga lekova")])],1)],1)},i=[],n=r("1da1"),o=r("5530"),u=(r("96cf"),r("2f62")),s={name:"Prijava",props:{value:Boolean},data:function(){return{email:"",lozinka:"",valid:!0,show:!1,rules:{required:function(t){return!!t||"Obavezno polje"},email:function(t){return/.+@.+\..+/.test(t)||"Email nije validan"}}}},computed:Object(o["a"])({},Object(u["c"])({currentMail:"korisnici/getMail",currentRole:"korisnici/getRole",korisnik:"korisnici/getKorisnik"})),methods:Object(o["a"])(Object(o["a"])({},Object(u["b"])({logInAction:"korisnici/logInAction"})),{},{cancel:function(){this.email="",this.lozinka=""},register:function(){this.$router.push("/registracija")},onSubmit:function(){var t=this;return Object(n["a"])(regeneratorRuntime.mark((function e(){var r,a;return regeneratorRuntime.wrap((function(e){while(1)switch(e.prev=e.next){case 0:return e.prev=0,r={mail:t.email,password:t.lozinka},e.next=4,t.logInAction(r);case 4:if("ROLE_PACIJENT"==t.currentRole){e.next=8;break}if(t.korisnik.promenjenaLozinka){e.next=8;break}return t.$router.push("/promenaSifre"),e.abrupt("return");case 8:a=t.currentRole,console.log(a),e.t0=a,e.next="ROLE_PACIJENT"===e.t0?13:"ROLE_DERMATOLOG"===e.t0?15:"ROLE_FARMACEUT"===e.t0?17:"ROLE_ADMIN_SISTEMA"===e.t0?19:"ROLE_ADMIN_APOTEKE"===e.t0?21:"ROLE_DOBAVLJAC"===e.t0?23:25;break;case 13:return t.$router.push("/pacijent"),e.abrupt("break",25);case 15:return t.$router.push("/dermatolog"),e.abrupt("break",25);case 17:return t.$router.push("/farmaceut"),e.abrupt("break",25);case 19:return t.$router.push("/admin"),e.abrupt("break",25);case 21:return t.$router.push("/adminApoteke"),e.abrupt("break",25);case 23:return t.$router.push("/dobavljac"),e.abrupt("break",25);case 25:e.next=31;break;case 27:e.prev=27,e.t1=e["catch"](0),console.log(e.t1),alert("Greska pri prijavi. Nekorektni kredencijali ili profil nije aktiviran");case 31:case"end":return e.stop()}}),e,null,[[0,27]])})))()},pretragaApoteka:function(){this.$router.push("/pretragaApoteka")},pretragaLekova:function(){this.$router.push("/pretragaLekova")}})},c=s,l=(r("daf2"),r("2877")),d=r("6544"),f=r.n(d),p=r("8336"),h=r("b0af"),v=r("99d9"),b=r("4bd4"),k=r("2fa4"),m=r("8654"),_=Object(l["a"])(c,a,i,!1,null,null,null);e["default"]=_.exports;f()(_,{VBtn:p["a"],VCard:h["a"],VCardActions:v["a"],VCardText:v["b"],VCardTitle:v["c"],VForm:b["a"],VSpacer:k["a"],VTextField:m["a"]})},"96f9":function(t,e,r){},"99d9":function(t,e,r){"use strict";r.d(e,"a",(function(){return n})),r.d(e,"b",(function(){return u})),r.d(e,"c",(function(){return s}));var a=r("b0af"),i=r("80d2"),n=Object(i["i"])("v-card__actions"),o=Object(i["i"])("v-card__subtitle"),u=Object(i["i"])("v-card__text"),s=Object(i["i"])("v-card__title");a["a"]},daf2:function(t,e,r){"use strict";r("96f9")}}]);
//# sourceMappingURL=chunk-1de4c64c.f687553a.js.map