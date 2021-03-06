sap.ui
		.define(
				[ 'sap/ui/core/mvc/Controller',
				  'sap/ui/core/routing/History',
				  'sap/m/MessageToast'],
				function(Controller, History, MessageToast) {
					"use strict";

					var PageController = Controller
							.extend(
									"net.cb.dcm.frontend.controller.list_device_procedures",
									{	
										relPath:"",
										onNavigateBack : function(event) {
											var oHistory = History.getInstance();
											var sPreviousHash = oHistory.getPreviousHash();

											if (sPreviousHash !== undefined) {
												window.history.go(-1);
											} else {
												var oRouter = sap.ui.core.UIComponent.getRouterFor(this);
												oRouter.navTo("Main", true);
											}
										},
										onInit : function(evt) {
											var loRouter = sap.ui.core.UIComponent.getRouterFor(this);
											
											var loRoute = loRouter.getRoute("ListDeviceProcedures");
											if (loRoute !== undefined) {												
												loRoute.attachMatched(this._onRouteMatched, this);
											}
											
										},
										_onRouteMatched : function (ioEvent) {
											var loArgs, loView;
											loArgs = ioEvent.getParameter("arguments");
											var lvId = loArgs.id;
											this.relPath = "/Devices(&1L)";
											this.relPath = this.relPath.replace("&1",lvId);
											this.updateTableBinding();
										},
										rowSelected : function(event) {
											var oRouter = sap.ui.core.UIComponent.getRouterFor(this);
											var lvId = event.getSource().getBindingContext().getProperty("Id");
											oRouter.navTo("EditDeviceProcedure", {id:lvId});
										},
										updateTableBinding: function(){
											var oTable = this.getView().byId("idProceduresTable"); 
											var oTemplate = new sap.m.ColumnListItem({
											    cells:[
											        new sap.m.ObjectIdentifier({title:"{Id}"}),
											        this.getView().byId("idLastExTime"),
											        this.getView().byId("idExTime"),
											        new sap.m.Text({text:"{ProcedureType}"}),
											        new sap.m.Text({text:"{ScheduleType}"}),
											        this.getView().byId("ShowProcedureDetailsBtn")
											        ]
											});//mode="SingleSelectMaster" selectionChange="rowSelected"
											var path = this.relPath + "/DeviceProcedureDetails";
											oTable.bindItems(path,oTemplate);
										}
									});

					return PageController;
				});