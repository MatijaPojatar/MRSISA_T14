(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-4b929c51"],{"0fd9":function(t,e,a){"use strict";var n=a("ade3"),r=a("5530"),o=(a("caad"),a("2532"),a("99af"),a("b64b"),a("5319"),a("ac1f"),a("4ec9"),a("d3b7"),a("3ca3"),a("ddb0"),a("159b"),a("4b85"),a("2b0e")),c=a("d9f7"),i=a("80d2"),s=["sm","md","lg","xl"],l=["start","end","center"];function u(t,e){return s.reduce((function(a,n){return a[t+Object(i["G"])(n)]=e(),a}),{})}var d=function(t){return[].concat(l,["baseline","stretch"]).includes(t)},f=u("align",(function(){return{type:String,default:null,validator:d}})),p=function(t){return[].concat(l,["space-between","space-around"]).includes(t)},v=u("justify",(function(){return{type:String,default:null,validator:p}})),h=function(t){return[].concat(l,["space-between","space-around","stretch"]).includes(t)},b=u("alignContent",(function(){return{type:String,default:null,validator:h}})),g={align:Object.keys(f),justify:Object.keys(v),alignContent:Object.keys(b)},m={align:"align",justify:"justify",alignContent:"align-content"};function j(t,e,a){var n=m[t];if(null!=a){if(e){var r=e.replace(t,"");n+="-".concat(r)}return n+="-".concat(a),n.toLowerCase()}}var k=new Map;e["a"]=o["a"].extend({name:"v-row",functional:!0,props:Object(r["a"])(Object(r["a"])(Object(r["a"])({tag:{type:String,default:"div"},dense:Boolean,noGutters:Boolean,align:{type:String,default:null,validator:d}},f),{},{justify:{type:String,default:null,validator:p}},v),{},{alignContent:{type:String,default:null,validator:h}},b),render:function(t,e){var a=e.props,r=e.data,o=e.children,i="";for(var s in a)i+=String(a[s]);var l=k.get(i);return l||function(){var t,e;for(e in l=[],g)g[e].forEach((function(t){var n=a[t],r=j(e,t,n);r&&l.push(r)}));l.push((t={"no-gutters":a.noGutters,"row--dense":a.dense},Object(n["a"])(t,"align-".concat(a.align),a.align),Object(n["a"])(t,"justify-".concat(a.justify),a.justify),Object(n["a"])(t,"align-content-".concat(a.alignContent),a.alignContent),t)),k.set(i,l)}(),t(a.tag,Object(c["a"])(r,{staticClass:"row",class:l}),o)}})},"4b85":function(t,e,a){},"4c5f":function(t,e,a){"use strict";a.r(e);var n=function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("v-card",{staticClass:"mt-16 mx-auto",attrs:{"min-width":"40%"}},[a("v-row",{attrs:{align:"center",justify:"space-around"}},[a("v-col",{attrs:{align:"center"}},[t._v(" Ocena: ")]),a("v-col",[a("v-checkbox",{attrs:{label:"< 2"},model:{value:t.ocena2,callback:function(e){t.ocena2=e},expression:"ocena2"}})],1),a("v-col",[a("v-checkbox",{attrs:{label:"2 - 4"},model:{value:t.ocena24,callback:function(e){t.ocena24=e},expression:"ocena24"}})],1),a("v-col",[a("v-checkbox",{attrs:{label:"> 4"},model:{value:t.ocena4,callback:function(e){t.ocena4=e},expression:"ocena4"}})],1)],1),a("v-divider"),a("v-row",{attrs:{align:"center",justify:"space-around"}},[a("v-col",{attrs:{align:"center"}},[t._v(" Udaljenost: ")]),a("v-col",[a("v-checkbox",{attrs:{label:"< 500"},model:{value:t.udaljenost5,callback:function(e){t.udaljenost5=e},expression:"udaljenost5"}})],1),a("v-col",[a("v-checkbox",{attrs:{label:"500 - 1000"},model:{value:t.udaljenost510,callback:function(e){t.udaljenost510=e},expression:"udaljenost510"}})],1),a("v-col",[a("v-checkbox",{attrs:{label:"> 1000"},model:{value:t.udaljenost10,callback:function(e){t.udaljenost10=e},expression:"udaljenost10"}})],1)],1),a("v-divider"),a("v-data-table",{attrs:{headers:t.headeri,items:t.filtriraneApoteke}}),a("v-divider"),a("v-row",{attrs:{"no-gutters":"",justify:"space-around"}},[a("v-col",{attrs:{md:"4"}},[a("v-text-field",{attrs:{label:"Naziv"},model:{value:t.pretragaParams.naziv,callback:function(e){t.$set(t.pretragaParams,"naziv",e)},expression:"pretragaParams.naziv"}})],1),a("v-col",{attrs:{md:"4"}},[a("v-text-field",{attrs:{label:"Grad"},model:{value:t.pretragaParams.grad,callback:function(e){t.$set(t.pretragaParams,"grad",e)},expression:"pretragaParams.grad"}})],1)],1),a("v-card-actions",{staticClass:"justify-center"},[a("v-btn",{on:{click:t.pretrazi}},[t._v(" Pretraži ")])],1)],1)},r=[],o=a("1da1"),c=(a("99af"),a("4de4"),a("caad"),a("2532"),a("159b"),a("96cf"),a("bc3a")),i=a.n(c),s={name:"PretragaApoteka",components:{},data:function(){return{headeri:[{text:"Naziv",value:"naziv",sortable:!1},{text:"Adresa",value:"adresa",sortable:!1},{text:"Grad",value:"grad",sortable:!1},{text:"Država",value:"drzava",sortable:!1},{text:"Ocena",value:"ocena",sortable:!1},{text:"Udaljenost",value:"udaljenost",sortable:!1}],apoteke:[],pretragaParams:{naziv:"",grad:""},ocena2:!0,ocena24:!0,ocena4:!0,udaljenost5:!0,udaljenost510:!0,udaljenost10:!0}},mounted:function(){var t=this;return Object(o["a"])(regeneratorRuntime.mark((function e(){return regeneratorRuntime.wrap((function(e){while(1)switch(e.prev=e.next){case 0:t.loadApoteke();case 1:case"end":return e.stop()}}),e)})))()},computed:{filtriraneApoteke:function(){var t=[],e=[];return this.ocena2&&(t=t.concat(this.apoteke.filter((function(t){return parseFloat(t.ocena)<2})))),this.ocena24&&(t=t.concat(this.apoteke.filter((function(t){return parseFloat(t.ocena)>=2&&parseFloat(t.ocena)<4})))),this.ocena4&&(t=t.concat(this.apoteke.filter((function(t){return parseFloat(t.ocena)>=4})))),this.udaljenost5&&(e=e.concat(this.apoteke.filter((function(t){return parseFloat(t.udaljenost)<500})))),this.udaljenost510&&(e=e.concat(this.apoteke.filter((function(t){return parseFloat(t.udaljenost)>=500&&parseFloat(t.udaljenost)<1e3})))),this.udaljenost10&&(e=e.concat(this.apoteke.filter((function(t){return parseFloat(t.udaljenost)>=1e3})))),t.filter((function(t){return e.includes(t)}))}},methods:{loadApoteke:function(){var t=this;return Object(o["a"])(regeneratorRuntime.mark((function e(){var a;return regeneratorRuntime.wrap((function(e){while(1)switch(e.prev=e.next){case 0:return a=[],e.next=3,i.a.get("http://localhost:8080/apoteke/sve/").then((function(e){e.data.forEach((function(e){a.push({naziv:e.naziv,adresa:e.adresa,grad:e.grad,drzava:e.drzava,ocena:e.id,udaljenost:500}),t.apoteke=a}))}));case 3:case"end":return e.stop()}}),e)})))()},pretrazi:function(){var t=this;i.a.post("http://localhost:8080/apoteke/pretraga",this.pretragaParams).then((function(e){t.apoteke=e.data,t.apoteke.forEach((function(t){t.ocena=t.id,t.udaljenost=500}))}))}}},l=s,u=a("2877"),d=a("6544"),f=a.n(d),p=a("8336"),v=a("b0af"),h=a("99d9"),b=a("ac7c"),g=a("62ad"),m=a("8fea"),j=a("ce7e"),k=a("0fd9"),x=a("8654"),y=Object(u["a"])(l,n,r,!1,null,null,null);e["default"]=y.exports;f()(y,{VBtn:p["a"],VCard:v["a"],VCardActions:h["a"],VCheckbox:b["a"],VCol:g["a"],VDataTable:m["a"],VDivider:j["a"],VRow:k["a"],VTextField:x["a"]})},"62ad":function(t,e,a){"use strict";var n=a("ade3"),r=a("5530"),o=(a("a9e3"),a("b64b"),a("5319"),a("ac1f"),a("4ec9"),a("d3b7"),a("3ca3"),a("ddb0"),a("caad"),a("159b"),a("2ca0"),a("4b85"),a("2b0e")),c=a("d9f7"),i=a("80d2"),s=["sm","md","lg","xl"],l=function(){return s.reduce((function(t,e){return t[e]={type:[Boolean,String,Number],default:!1},t}),{})}(),u=function(){return s.reduce((function(t,e){return t["offset"+Object(i["G"])(e)]={type:[String,Number],default:null},t}),{})}(),d=function(){return s.reduce((function(t,e){return t["order"+Object(i["G"])(e)]={type:[String,Number],default:null},t}),{})}(),f={col:Object.keys(l),offset:Object.keys(u),order:Object.keys(d)};function p(t,e,a){var n=t;if(null!=a&&!1!==a){if(e){var r=e.replace(t,"");n+="-".concat(r)}return"col"!==t||""!==a&&!0!==a?(n+="-".concat(a),n.toLowerCase()):n.toLowerCase()}}var v=new Map;e["a"]=o["a"].extend({name:"v-col",functional:!0,props:Object(r["a"])(Object(r["a"])(Object(r["a"])(Object(r["a"])({cols:{type:[Boolean,String,Number],default:!1}},l),{},{offset:{type:[String,Number],default:null}},u),{},{order:{type:[String,Number],default:null}},d),{},{alignSelf:{type:String,default:null,validator:function(t){return["auto","start","end","center","baseline","stretch"].includes(t)}},tag:{type:String,default:"div"}}),render:function(t,e){var a=e.props,r=e.data,o=e.children,i=(e.parent,"");for(var s in a)i+=String(a[s]);var l=v.get(i);return l||function(){var t,e;for(e in l=[],f)f[e].forEach((function(t){var n=a[t],r=p(e,t,n);r&&l.push(r)}));var r=l.some((function(t){return t.startsWith("col-")}));l.push((t={col:!r||!a.cols},Object(n["a"])(t,"col-".concat(a.cols),a.cols),Object(n["a"])(t,"offset-".concat(a.offset),a.offset),Object(n["a"])(t,"order-".concat(a.order),a.order),Object(n["a"])(t,"align-self-".concat(a.alignSelf),a.alignSelf),t)),v.set(i,l)}(),t(a.tag,Object(c["a"])(r,{class:l}),o)}})},"6ca7":function(t,e,a){},ac7c:function(t,e,a){"use strict";var n=a("5530"),r=(a("d3b7"),a("25f0"),a("6ca7"),a("ec29"),a("9d26")),o=a("c37a"),c=a("fe09");e["a"]=c["a"].extend({name:"v-checkbox",props:{indeterminate:Boolean,indeterminateIcon:{type:String,default:"$checkboxIndeterminate"},offIcon:{type:String,default:"$checkboxOff"},onIcon:{type:String,default:"$checkboxOn"}},data:function(){return{inputIndeterminate:this.indeterminate}},computed:{classes:function(){return Object(n["a"])(Object(n["a"])({},o["a"].options.computed.classes.call(this)),{},{"v-input--selection-controls":!0,"v-input--checkbox":!0,"v-input--indeterminate":this.inputIndeterminate})},computedIcon:function(){return this.inputIndeterminate?this.indeterminateIcon:this.isActive?this.onIcon:this.offIcon},validationState:function(){if(!this.isDisabled||this.inputIndeterminate)return this.hasError&&this.shouldValidate?"error":this.hasSuccess?"success":null!==this.hasColor?this.computedColor:void 0}},watch:{indeterminate:function(t){var e=this;this.$nextTick((function(){return e.inputIndeterminate=t}))},inputIndeterminate:function(t){this.$emit("update:indeterminate",t)},isActive:function(){this.indeterminate&&(this.inputIndeterminate=!1)}},methods:{genCheckbox:function(){return this.$createElement("div",{staticClass:"v-input--selection-controls__input"},[this.$createElement(r["a"],this.setTextColor(this.validationState,{props:{dense:this.dense,dark:this.dark,light:this.light}}),this.computedIcon),this.genInput("checkbox",Object(n["a"])(Object(n["a"])({},this.attrs$),{},{"aria-checked":this.inputIndeterminate?"mixed":this.isActive.toString()})),this.genRipple(this.setTextColor(this.rippleState))])},genDefaultSlot:function(){return[this.genCheckbox(),this.genLabel()]}}})},afdd:function(t,e,a){"use strict";var n=a("8336");e["a"]=n["a"]}}]);
//# sourceMappingURL=chunk-4b929c51.936e3bbd.js.map