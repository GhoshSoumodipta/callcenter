package at.ac.tuwien.sepm.groupphase.backend.datatype;

import java.util.Arrays;

public enum CompanyType {
    COMPANY,
    SWITCHINGCENTER;

    public static CompanyType getTypeByName(String name) {
        return Arrays.stream(CompanyType.values())
            .filter(enumUserType -> enumUserType.name().equals(name))
            .findFirst()
            .orElse(null);
    }
}

/* There should be all types of Users:
* - ADMIN
*   -- Admin can create Companies-Leader-Account or new accounts (all position, like customer, self-employed, project manager, employees, switching center)
*   -- Admin can block and unblock User
*   -- Admin can delete user
*   -- Admin can send a resetting password to user.
*   -- Admin can also accept videocalls and do videocalls to other persons
*   -- Can see the calendar of free interpreters
* _________________________________________________________________________
* - COMPANY-LEADER
*   -- Can create own employee
*   -- Can create online-time-table for every own employee
*   -- Can be reached by everyone
*   -- Get invoices from Admin
* _________________________________________________________________________
* - COMPANY-EMPLOYEE
*   -- Can be reached by everyone by external link or on platform and it’s completely free for customers
* _________________________________________________________________________
* - SWITCHING-CENTER-LEADER
*   -- Can create new customer and own employee (user only for their province)
*   -- Can be reached by customer and have online-time-table
*   -- Have control of own employee
*   -- Can check Data to their customer
*   -- Can add balance
*   -- Can send out new orders to all free translators all online, or only local in same province if it should be present)
*   -- Check certificate for translators (only for their province)
*   -- Has own link to call (free for everyone and anonymous)
*   -- Can create invoices from/to translators
*   -- Can create orders for own customers
*   -- Can see the history of own customers
*   -- Get invoices from Admin
*   -- Can see the calendar of free interpreters
* _________________________________________________________________________
* - SWITCHING-CENTER-EMPLOYEE
*   -- Can be reached by customer and have online-time-table
*   -- Can check Data to their customer
*   -- Can add balance
*   -- Can send out new orders to all free translators (all online, or only local in same province if it should be present)
*   -- Check certificate for translators (only for their province)
*   -- Has own link to call (free for everyone and anonymous)
*   -- Can create invoices from/to translators
*   -- Can see the history of own customers
*   -- Can be blocked by switching center leader or admin
*   -- Can see the calendar of free interpreters
* _________________________________________________________________________
* - INTERPRETER
*   -- Can be reached by customer only at appointments or call them.
*   -- Can be reached by switching center when interpreters are online and free.
*   -- Can accept or denied orders from switching center or customer itself
*   -- Get invoices from admin or/and from switching center
*   -- Can be blocked/unblocked by switching center or admin
*   -- Must be certificate by switching center or admin (also SMS-certification)
* _________________________________________________________________________
* - CUSTOMERS
*   -- Can ask for appointment when he has enough balance
*   -- Can call company or own switching center
*   -- Can see the calendar of free interpreters
*   -- Must be certificate by switching center or admin (also SMS-certification)
*   -- Can call translator if they have appointments
* _________________________________________________________________________
* (- ANONYMOUS
*    -- Can call company)
* */