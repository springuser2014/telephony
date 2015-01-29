
var Telephony = Telephony || {} ;

Telephony.Rest = { };

Telephony.Rest.Url = {
    Server : '/telephony-ws/rest',
    Session : {
        Initialize : '/session/initialize',
        Refresh : '/session/refresh',
        Validate : '/session/validate',
        Destroy : '/session/destroy',
        Details : '/session/details'
    },
    Complaints : {
        MarkAsResolved : '/complaint/markAsResolved',
        MarkAsRejected : '/complaint/markAsRejected',
        MarkAsInProgress : '/complaint/markAsInProgress',
        MarkAsAccepted : '/complaint/markAsAccepted',
        Comment : '/complaint/comment',
        CommentAnonymous : '/complaint/commentAnonymous',
        Delete : '/complaint/delete'
    },
    ProductComplaints : {
        Add : '/product/complaint/add',
        Fetch : '/product/complaint/fetch',
        Edit : '/sale/complaint/edit',
        Details : '/sale/complaint/details'
    },
    SaleComplaints : {
        Add : '/sale/complaint/add',
        Fetch : '/sale/complaint/fetch',
        Edit : '/sale/complaint/edit',
        Details : '/sale/complaint/details'
    },
    Sales : {
        Add : '/sales/add',
        Delete : '/sales/delete',
        Details : '/sales/details',
        Edit : '/sales/edit',
        Fetch : '/sales/fetch'
    },
    Deliveries : {
        Add : '/deliveries/add',
        Delete : '/deliveries/delete',
        Details : '/deliveries/details',
        Edit : '/deliveries/edit',
        Fetch : '/deliveries/fetch'
    },
    Users : {
        Fetch : '/users/fetch',
        Edit : '/users/edit',
        Delete : '/users/delete',
        Add : '/users/add'
    },
    Contacts : {
        Add : '/contacts/add',
        Fetch : '/contacts/fetch',
        Edit : '/contacts/edit',
        Details : '/contacts/details',
        Delete : '/contacts/delete'
    },
    Taxes : {
        Fetch : '/taxes/fetch',
        Edit : '/taxes/edit',
        Delete : '/taxes/delete',
        Add : '/taxes/add'
    },
    Stores : {
        Fetch : '/stores/fetch',
        Edit : '/stores/edit',
        Delete : '/stores/delete',
        Add : '/stores/add'
    },
    Products : {
        Fetch : '/products/fetch',
        Details : '/products/details',
        FetchData : '/products/fetchData',
        IsImeiAvailable : '/product/isImeiAvailable'
    },
    Roles : {
        Fetch : '/roles/fetch'
    }
};

Telephony.Rest.Complaints = {

    Comment : function(data, completeFunc, errorFunc, async) {

        if (typeof async == "undefined") {
            async = true;
        }
        $.ajax({
            async : async,
            url: Telephony.Rest.Url.Server + Telephony.Rest.Url.Complaints.Comment,
            type: 'POST',
            headers : {
                'Content-type' : 'application/json'
            },
            dataType : 'json',
            data : JSON.stringify(data),
            complete : completeFunc,
            error : errorFunc
        });
    },

    CommentAnonymous : function(data, completeFunc, errorFunc, async) {

        if (typeof async == "undefined") {
            async = true;
        }
        $.ajax({
            async : async,
            url: Telephony.Rest.Url.Server + Telephony.Rest.Url.Complaints.CommentAnonymous,
            type: 'POST',
            headers : {
                'Content-type' : 'application/json'
            },
            dataType : 'json',
            data : JSON.stringify(data),
            complete : completeFunc,
            error : errorFunc
        });
    },

    Delete : function(data, completeFunc, errorFunc, async) {

        if (typeof async == "undefined") {
            async = true;
        }
        $.ajax({
            async : async,
            url: Telephony.Rest.Url.Server + Telephony.Rest.Url.Complaints.Delete,
            type: 'POST',
            headers : {
                'Content-type' : 'application/json'
            },
            dataType : 'json',
            data : JSON.stringify(data),
            complete : completeFunc,
            error : errorFunc
        });
    },

    MarkAsResolved : function(data, completeFunc, errorFunc, async) {

        if (typeof async == "undefined") {
            async = true;
        }
        $.ajax({
            async : async,
            url: Telephony.Rest.Url.Server + Telephony.Rest.Url.Complaints.MarkAsResolved,
            type: 'POST',
            headers : {
                'Content-type' : 'application/json'
            },
            dataType : 'json',
            data : JSON.stringify(data),
            complete : completeFunc,
            error : errorFunc
        });
    },

    MarkAsAccepted : function(data, completeFunc, errorFunc, async) {

        if (typeof async == "undefined") {
            async = true;
        }
        $.ajax({
            async : async,
            url: Telephony.Rest.Url.Server + Telephony.Rest.Url.Complaints.MarkAsAccepted,
            type: 'POST',
            headers : {
                'Content-type' : 'application/json'
            },
            dataType : 'json',
            data : JSON.stringify(data),
            complete : completeFunc,
            error : errorFunc
        });
    },

    MarkAsInProgress : function(data, completeFunc, errorFunc, async) {

        if (typeof async == "undefined") {
            async = true;
        }
        $.ajax({
            async : async,
            url: Telephony.Rest.Url.Server + Telephony.Rest.Url.Complaints.MarkAsInProgress,
            type: 'POST',
            headers : {
                'Content-type' : 'application/json'
            },
            dataType : 'json',
            data : JSON.stringify(data),
            complete : completeFunc,
            error : errorFunc
        });
    },

    MarkAsRejected : function(data, completeFunc, errorFunc, async) {

        if (typeof async == "undefined") {
            async = true;
        }
        $.ajax({
            async : async,
            url: Telephony.Rest.Url.Server + Telephony.Rest.Url.Complaints.MarkAsRejected,
            type: 'POST',
            headers : {
                'Content-type' : 'application/json'
            },
            dataType : 'json',
            data : JSON.stringify(data),
            complete : completeFunc,
            error : errorFunc
        });
    }
};

Telephony.Rest.SaleComplaints = {
    Fetch : function(data, completeFunc, errorFunc, async) {

        if (typeof async == "undefined") {
            async = true;
        }
        $.ajax({
            async : async,
            url: Telephony.Rest.Url.Server + Telephony.Rest.Url.SaleComplaints.Fetch,
            type: 'POST',
            headers : {
                'Content-type' : 'application/json'
            },
            dataType : 'json',
            data : JSON.stringify(data),
            complete : completeFunc,
            error : errorFunc
        });
    },

    Details : function(data, completeFunc, errorFunc, async) {

        if (typeof async == "undefined") {
            async = true;
        }
        $.ajax({
            async : async,
            url: Telephony.Rest.Url.Server + Telephony.Rest.Url.SaleComplaints.Details,
            type: 'POST',
            headers : {
                'Content-type' : 'application/json'
            },
            dataType : 'json',
            data : JSON.stringify(data),
            complete : completeFunc,
            error : errorFunc
        });
    },

    Edit : function(data, completeFunc, errorFunc, async) {

        if (typeof async == "undefined") {
            async = true;
        }
        $.ajax({
            async : async,
            url: Telephony.Rest.Url.Server + Telephony.Rest.Url.SaleComplaints.Edit,
            type: 'PUT',
            headers : {
                'Content-type' : 'application/json'
            },
            dataType : 'json',
            data : JSON.stringify(data),
            complete : completeFunc,
            error : errorFunc
        });
    },

    Add : function(data, completeFunc, errorFunc, async) {

        if (typeof async == "undefined") {
            async = true;
        }
        $.ajax({
            async : async,
            url: Telephony.Rest.Url.Server + Telephony.Rest.Url.SaleComplaints.Add,
            type: 'POST',
            headers : {
                'Content-type' : 'application/json'
            },
            dataType : 'json',
            data : JSON.stringify(data),
            complete : completeFunc,
            error : errorFunc
        });
    }
};

Telephony.Rest.ProductComplaints = {
    Fetch : function(data, completeFunc, errorFunc, async) {

        if (typeof async == "undefined") {
            async = true;
        }
        $.ajax({
            async : async,
            url: Telephony.Rest.Url.Server + Telephony.Rest.Url.ProductComplaints.Fetch,
            type: 'POST',
            headers : {
                'Content-type' : 'application/json'
            },
            dataType : 'json',
            data : JSON.stringify(data),
            complete : completeFunc,
            error : errorFunc
        });
    },

    Details : function(data, completeFunc, errorFunc, async) {

        if (typeof async == "undefined") {
            async = true;
        }
        $.ajax({
            async : async,
            url: Telephony.Rest.Url.Server + Telephony.Rest.Url.ProductComplaints.Details,
            type: 'POST',
            headers : {
                'Content-type' : 'application/json'
            },
            dataType : 'json',
            data : JSON.stringify(data),
            complete : completeFunc,
            error : errorFunc
        });
    },

    Edit : function(data, completeFunc, errorFunc, async) {

        if (typeof async == "undefined") {
            async = true;
        }
        $.ajax({
            async : async,
            url: Telephony.Rest.Url.Server + Telephony.Rest.Url.ProductComplaints.Edit,
            type: 'PUT',
            headers : {
                'Content-type' : 'application/json'
            },
            dataType : 'json',
            data : JSON.stringify(data),
            complete : completeFunc,
            error : errorFunc
        });
    },

    Add : function(data, completeFunc, errorFunc, async) {

        if (typeof async == "undefined") {
            async = true;
        }
        $.ajax({
            async : async,
            url: Telephony.Rest.Url.Server + Telephony.Rest.Url.ProductComplaints.Add,
            type: 'POST',
            headers : {
                'Content-type' : 'application/json'
            },
            dataType : 'json',
            data : JSON.stringify(data),
            complete : completeFunc,
            error : errorFunc
        });
    }
};

Telephony.Rest.Sales = {
    Fetch : function(data, completeFunc, errorFunc, async) {

        if (typeof async == "undefined") {
            async = true;
        }
        $.ajax({
            async : async,
            url: Telephony.Rest.Url.Server + Telephony.Rest.Url.Sales.Fetch,
            type: 'POST',
            headers : {
                'Content-type' : 'application/json'
            },
            dataType : 'json',
            data : JSON.stringify(data),
            complete : completeFunc,
            error : errorFunc
        });
    },

    Details : function(data, completeFunc, errorFunc, async) {

        if (typeof async == "undefined") {
            async = true;
        }
        $.ajax({
            async : async,
            url: Telephony.Rest.Url.Server + Telephony.Rest.Url.Sales.Details,
            type: 'POST',
            headers : {
                'Content-type' : 'application/json'
            },
            dataType : 'json',
            data : JSON.stringify(data),
            complete : completeFunc,
            error : errorFunc
        });
    },

    Edit : function(data, completeFunc, errorFunc, async) {

        if (typeof async == "undefined") {
            async = true;
        }
        $.ajax({
            async : async,
            url: Telephony.Rest.Url.Server + Telephony.Rest.Url.Sales.Edit,
            type: 'PUT',
            headers : {
                'Content-type' : 'application/json'
            },
            dataType : 'json',
            data : JSON.stringify(data),
            complete : completeFunc,
            error : errorFunc
        });
    },

    Delete : function(data, completeFunc, errorFunc, async) {

        if (typeof async == "undefined") {
            async = true;
        }
        $.ajax({
            async : async,
            url: Telephony.Rest.Url.Server + Telephony.Rest.Url.Sales.Delete,
            type: 'DELETE',
            headers : {
                'Content-type' : 'application/json'
            },
            dataType : 'json',
            data : JSON.stringify(data),
            complete : completeFunc,
            error : errorFunc
        });
    },

    Add : function(data, completeFunc, errorFunc, async) {

        if (typeof async == "undefined") {
            async = true;
        }
        $.ajax({
            async : async,
            url: Telephony.Rest.Url.Server + Telephony.Rest.Url.Sales.Add,
            type: 'POST',
            headers : {
                'Content-type' : 'application/json'
            },
            dataType : 'json',
            data : JSON.stringify(data),
            complete : completeFunc,
            error : errorFunc
        });
    }
};

Telephony.Rest.Deliveries = {
    Fetch : function(data, completeFunc, errorFunc, async) {

        if (typeof async == "undefined") {
            async = true;
        }
        $.ajax({
            async : async,
            url: Telephony.Rest.Url.Server + Telephony.Rest.Url.Deliveries.Fetch,
            type: 'POST',
            headers : {
                'Content-type' : 'application/json'
            },
            dataType : 'json',
            data : JSON.stringify(data),
            complete : completeFunc,
            error : errorFunc
        });
    },

    Details : function(data, completeFunc, errorFunc, async) {

        if (typeof async == "undefined") {
            async = true;
        }
        $.ajax({
            async : async,
            url: Telephony.Rest.Url.Server + Telephony.Rest.Url.Deliveries.Details,
            type: 'POST',
            headers : {
                'Content-type' : 'application/json'
            },
            dataType : 'json',
            data : JSON.stringify(data),
            complete : completeFunc,
            error : errorFunc
        });
    },

    Edit : function(data, completeFunc, errorFunc, async) {

        if (typeof async == "undefined") {
            async = true;
        }
        $.ajax({
            async : async,
            url: Telephony.Rest.Url.Server + Telephony.Rest.Url.Deliveries.Edit,
            type: 'PUT',
            headers : {
                'Content-type' : 'application/json'
            },
            dataType : 'json',
            data : JSON.stringify(data),
            complete : completeFunc,
            error : errorFunc
        });
    },

    Delete : function(data, completeFunc, errorFunc, async) {

        if (typeof async == "undefined") {
            async = true;
        }
        $.ajax({
            async : async,
            url: Telephony.Rest.Url.Server + Telephony.Rest.Url.Deliveries.Delete,
            type: 'DELETE',
            headers : {
                'Content-type' : 'application/json'
            },
            dataType : 'json',
            data : JSON.stringify(data),
            complete : completeFunc,
            error : errorFunc
        });
    },

    Add : function(data, completeFunc, errorFunc, async) {

        if (typeof async == "undefined") {
            async = true;
        }
        $.ajax({
            async : async,
            url: Telephony.Rest.Url.Server + Telephony.Rest.Url.Deliveries.Add,
            type: 'POST',
            headers : {
                'Content-type' : 'application/json'
            },
            dataType : 'json',
            data : JSON.stringify(data),
            complete : completeFunc,
            error : errorFunc
        });
    }
};

Telephony.Rest.Contacts = {
    Fetch : function(data, completeFunc, errorFunc, async) {

        if (typeof async == "undefined") {
            async = true;
        }
        $.ajax({
            async : async,
            url: Telephony.Rest.Url.Server + Telephony.Rest.Url.Contacts.Fetch,
            type: 'POST',
            headers : {
                'Content-type' : 'application/json'
            },
            dataType : 'json',
            data : JSON.stringify(data),
            complete : completeFunc,
            error : errorFunc
        });
    },

    Details : function(data, completeFunc, errorFunc, async) {

        if (typeof async == "undefined") {
            async = true;
        }
        $.ajax({
            async : async,
            url: Telephony.Rest.Url.Server + Telephony.Rest.Url.Contacts.Details,
            type: 'POST',
            headers : {
                'Content-type' : 'application/json'
            },
            dataType : 'json',
            data : JSON.stringify(data),
            complete : completeFunc,
            error : errorFunc
        });
    },

    Edit : function(data, completeFunc, errorFunc, async) {

        if (typeof async == "undefined") {
            async = true;
        }
        $.ajax({
            async : async,
            url: Telephony.Rest.Url.Server + Telephony.Rest.Url.Contacts.Edit,
            type: 'PUT',
            headers : {
                'Content-type' : 'application/json'
            },
            dataType : 'json',
            data : JSON.stringify(data),
            complete : completeFunc,
            error : errorFunc
        });
    },

    Delete : function(data, completeFunc, errorFunc, async) {

        if (typeof async == "undefined") {
            async = true;
        }
        $.ajax({
            async : async,
            url: Telephony.Rest.Url.Server + Telephony.Rest.Url.Contacts.Delete,
            type: 'DELETE',
            headers : {
                'Content-type' : 'application/json'
            },
            dataType : 'json',
            data : JSON.stringify(data),
            complete : completeFunc,
            error : errorFunc
        });
    },

    Add : function(data, completeFunc, errorFunc, async) {

        if (typeof async == "undefined") {
            async = true;
        }
        $.ajax({
            async : async,
            url: Telephony.Rest.Url.Server + Telephony.Rest.Url.Contacts.Add,
            type: 'POST',
            headers : {
                'Content-type' : 'application/json'
            },
            dataType : 'json',
            data : JSON.stringify(data),
            complete : completeFunc,
            error : errorFunc
        });
    }
};

Telephony.Rest.Products = {
    Fetch: function (data, completeFunc, errorFunc, async) {

        if (typeof async == "undefined") {
            async = true;
        }
        $.ajax({
            async : async,
            url: Telephony.Rest.Url.Server + Telephony.Rest.Url.Products.Fetch,
            type: 'POST',
            headers: {
                'Content-type': 'application/json'
            },
            dataType: 'json',
            data: JSON.stringify(data),
            complete: completeFunc,
            error: errorFunc
        });
    },

    IsImeiAvailable: function (data, completeFunc, errorFunc, async) {

        if (typeof async == "undefined") {
            async = true;
        }
        $.ajax({
            async : async,
            url: Telephony.Rest.Url.Server + Telephony.Rest.Url.Products.IsImeiAvailable,
            type: 'POST',
            headers: {
                'Content-type': 'application/json'
            },
            dataType: 'json',
            data: JSON.stringify(data),
            complete: completeFunc,
            error: errorFunc
        });
    },

    FetchData: function (data, completeFunc, errorFunc, async) {

        if (typeof async == "undefined") {
            async = true;
        }
        $.ajax({
            async : async,
            url: Telephony.Rest.Url.Server + Telephony.Rest.Url.Products.FetchData,
            type: 'POST',
            headers: {
                'Content-type': 'application/json'
            },
            dataType: 'json',
            data: JSON.stringify(data),
            complete: completeFunc,
            error: errorFunc
        });
    },

    Details: function (data, completeFunc, errorFunc, async) {

        if (typeof async == "undefined") {
            async = true;
        }
        $.ajax({
            async : async,
            url: Telephony.Rest.Url.Server + Telephony.Rest.Url.Products.Details,
            type: 'POST',
            headers: {
                'Content-type': 'application/json'
            },
            dataType: 'json',
            data: JSON.stringify(data),
            complete: completeFunc,
            error: errorFunc
        });
    }
};

Telephony.Rest.Roles = {
    Fetch: function (data, completeFunc, errorFunc, async) {

        if (typeof async == "undefined") {
            async = true;
        }
        $.ajax({
            async : async,
            url: Telephony.Rest.Url.Server + Telephony.Rest.Url.Roles.Fetch,
            type: 'POST',
            headers: {
                'Content-type': 'application/json'
            },
            dataType: 'json',
            data: JSON.stringify(data),
            complete: completeFunc,
            error: errorFunc
        });
    }
};

Telephony.Rest.Stores = {
    Fetch : function(data, completeFunc, errorFunc, async) {

        if (typeof async === "undefined") {
            async = true;
        }

        $.ajax({
            async : async,
            url: Telephony.Rest.Url.Server + Telephony.Rest.Url.Stores.Fetch,
            type: 'POST',
            headers : {
                'Content-type' : 'application/json'
            },
            dataType : 'json',
            data : JSON.stringify(data),
            complete : completeFunc,
            error : errorFunc
        });
    },

    Edit : function(data, completeFunc, errorFunc, async) {

        if (typeof async == "undefined") {
            async = true;
        }
        $.ajax({
            async : async,
            url: Telephony.Rest.Url.Server + Telephony.Rest.Url.Stores.Edit,
            type: 'PUT',
            headers : {
                'Content-type' : 'application/json'
            },
            dataType : 'json',
            data : JSON.stringify(data),
            complete : completeFunc,
            error : errorFunc
        });
    },

    Delete : function(data, completeFunc, errorFunc, async) {

        if (typeof async == "undefined") {
            async = true;
        }
        $.ajax({
            async : async,
            url: Telephony.Rest.Url.Server + Telephony.Rest.Url.Stores.Delete,
            type: 'DELETE',
            headers : {
                'Content-type' : 'application/json'
            },
            dataType : 'json',
            data : JSON.stringify(data),
            complete : completeFunc,
            error : errorFunc
        });
    },

    Add : function(data, completeFunc, errorFunc, async) {

        if (typeof async == "undefined") {
            async = true;
        }
        $.ajax({
            async : async,
            url: Telephony.Rest.Url.Server + Telephony.Rest.Url.Stores.Add,
            type: 'POST',
            headers : {
                'Content-type' : 'application/json'
            },
            dataType : 'json',
            data : JSON.stringify(data),
            complete : completeFunc,
            error : errorFunc
        });
    }
};

Telephony.Rest.Taxes = {
    Fetch : function(data, completeFunc, errorFunc, async) {

        if (typeof async == "undefined") {
            async = true;
        }
        $.ajax({
            async : async,
            url: Telephony.Rest.Url.Server + Telephony.Rest.Url.Taxes.Fetch,
            type: 'POST',
            headers : {
                'Content-type' : 'application/json'
            },
            dataType : 'json',
            data : JSON.stringify(data),
            complete : completeFunc,
            error : errorFunc
        });
    },

    Edit : function(data, completeFunc, errorFunc, async) {

        if (typeof async == "undefined") {
            async = true;
        }
        $.ajax({
            async : async,
            url: Telephony.Rest.Url.Server + Telephony.Rest.Url.Taxes.Edit,
            type: 'PUT',
            headers : {
                'Content-type' : 'application/json'
            },
            dataType : 'json',
            data : JSON.stringify(data),
            complete : completeFunc,
            error : errorFunc
        });
    },

    Delete : function(data, completeFunc, errorFunc, async) {

        if (typeof async == "undefined") {
            async = true;
        }
        $.ajax({
            async : async,
            url: Telephony.Rest.Url.Server + Telephony.Rest.Url.Taxes.Delete,
            type: 'DELETE',
            headers : {
                'Content-type' : 'application/json'
            },
            dataType : 'json',
            data : JSON.stringify(data),
            complete : completeFunc,
            error : errorFunc
        });
    },

    Add : function(data, completeFunc, errorFunc, async) {

        if (typeof async == "undefined") {
            async = true;
        }
        $.ajax({
            async : async,
            url: Telephony.Rest.Url.Server + Telephony.Rest.Url.Taxes.Add,
            type: 'POST',
            headers : {
                'Content-type' : 'application/json'
            },
            dataType : 'json',
            data : JSON.stringify(data),
            complete : completeFunc,
            error : errorFunc
        });
    }
};

Telephony.Rest.Users = {
    Fetch : function(data, completeFunc, errorFunc, async) {

        if (typeof async == "undefined") {
            async = true;
        }
        $.ajax({
            async : async,
            url: Telephony.Rest.Url.Server + Telephony.Rest.Url.Users.Fetch,
            type: 'POST',
            headers : {
                'Content-type' : 'application/json'
            },
            dataType : 'json',
            data : JSON.stringify(data),
            complete : completeFunc,
            error : errorFunc
        });
    },

    Edit : function(data, completeFunc, errorFunc, async) {

        if (typeof async == "undefined") {
            async = true;
        }
        $.ajax({
            async : async,
            url: Telephony.Rest.Url.Server + Telephony.Rest.Url.Users.Edit,
            type: 'PUT',
            headers : {
                'Content-type' : 'application/json'
            },
            dataType : 'json',
            data : JSON.stringify(data),
            complete : completeFunc,
            error : errorFunc
        });
    },

    Delete : function(data, completeFunc, errorFunc, async) {

        if (typeof async == "undefined") {
            async = true;
        }
        $.ajax({
            async : async,
            url: Telephony.Rest.Url.Server + Telephony.Rest.Url.Users.Delete,
            type: 'DELETE',
            headers : {
                'Content-type' : 'application/json'
            },
            dataType : 'json',
            data : JSON.stringify(data),
            complete : completeFunc,
            error : errorFunc
        });
    },

    Add : function(data, completeFunc, errorFunc, async) {

        if (typeof async == "undefined") {
            async = true;
        }
        $.ajax({
            async : async,
            url: Telephony.Rest.Url.Server + Telephony.Rest.Url.Users.Add,
            type: 'POST',
            headers : {
                'Content-type' : 'application/json'
            },
            dataType : 'json',
            data : JSON.stringify(data),
            complete : completeFunc,
            error : errorFunc
        });
    }
};

Telephony.Rest.Session = {

    Initialize : function(data, completeFunc, errorFunc, async) {

        if (typeof async == "undefined") {
            async = true;
        }
        $.ajax({
            async : async,
            url: Telephony.Rest.Url.Server + Telephony.Rest.Url.Session.Initialize,
            type: 'POST',
            headers : {
                'Content-type' : 'application/json'
            },
            dataType : 'json',
            data : JSON.stringify(data),
            complete : completeFunc,
            error : errorFunc
        });
    },

    Refresh : function(data, completeFunc, errorFunc, async) {

        if (typeof async == "undefined") {
            async = true;
        }
        $.ajax({
            async : async,
            url: Telephony.Rest.Url.Server + Telephony.Rest.Url.Session.Refresh,
            type: 'POST',
            headers : {
                'Content-type' : 'application/json'
            },
            dataType : 'json',
            data : JSON.stringify(data),
            complete : completeFunc,
            error : errorFunc
        });
    },

    Validate : function(data, completeFunc, errorFunc, async) {

        if (typeof async == "undefined") {
            async = true;
        }
        $.ajax({
            async : async,
            url: Telephony.Rest.Url.Server + Telephony.Rest.Url.Session.Validate,
            type: 'POST',
            headers : {
                'Content-type' : 'application/json'
            },
            dataType : 'json',
            data : JSON.stringify(data),
            complete : completeFunc,
            error : errorFunc
        });
    },

    Destroy : function(data, completeFunc, errorFunc, async) {

        if (typeof async == "undefined") {
            async = true;
        }
        $.ajax({
            async : async,
            url: Telephony.Rest.Url.Server + Telephony.Rest.Url.Session.Destroy,
            type: 'DELETE',
            headers : {
                'Content-type' : 'application/json'
            },
            dataType : 'json',
            data : JSON.stringify(data),
            complete : completeFunc,
            error : errorFunc
        });
    },

    Details : function(data, completeFunc, errorFunc, async) {

        if (typeof async == "undefined") {
            async = true;
        }
        $.ajax({
            async : async,
            url: Telephony.Rest.Url.Server + Telephony.Rest.Url.Session.Details,
            type: 'POST',
            headers : {
                'Content-type' : 'application/json'
            },
            dataType : 'json',
            data : JSON.stringify(data),
            complete : completeFunc,
            error : errorFunc
        });
    }
};
