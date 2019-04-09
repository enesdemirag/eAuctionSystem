## Coursework: eAuction System

<img align="right" src="http://www.ljmuenglish.com/wp-content/uploads/2017/08/unilogo_90.jpg" height="102" width="354">

- Faculty Name: **Faculty of Engineering & Technology**
- Department Name: **Department of Computer Science**
- Module Name: **Object Oriented Systems**
- Module Code: **5104COMP**
- Level: **5**
- Credit Rating: **20**
- Lecturer: **Mr. Glyn Hughes**

---

### Details of Part 1:

The eAuction system allows multiple users to conduct auctions electronically. Once a user has setup an account, they are able to act as a seller and start an auction by listing an item to be sold. They may also act as a buyer by browsing auctions that are in progress and bidding on any item whose auction has not closed. A user does not need to setup an account to simply browse auctions in progress.

An auction is started when a seller inputs data including, a description of the item, the starting price, the reserve price and a date when the auction will close. The system enforces an upper and lower bidding increment of 20% and 10% of the starting price respectively. The system also enforces a closing date ≤ 7 days from the current date.

Once the data has been input, the auction automatically becomes pending and the seller must verify the auction before it starts.

When a user wants to make a bid against an item, they must first locate the auction in question by browsing the auctions that are in progress. They may then make a bid that the system must check is within the upper and lower bidding increment amounts.

Each auction keeps track of every bid made against the item. Once the auction closes the system checks to see if the item’s reserve price has been met and if so, informs the buyer with the highest bid of their victory. If the item’s reserve price has not been met, the system informs every buyer who made a bid and the auction is closed.

Both sellers and auctions may be temporarily blocked which prevents the sellers from logging on or the auctions from being browsed or bid upon respectively.

You should produce..

- UML **Use Case** Diagram.
    - Try to use the << uses >> arrow to relate functionality.

- List of **Nouns** from the **Requirements Document**.

- Revised list of **Nouns** that specify **Candidate Classes**.
    - Justify why you exclude any Noun.
    - Identify any Noun that might become an Attribute.

- UML **Class** Diagram.
    - Make appropriate use of associations, compositions & aggregations and ensure that you specify multiplicity.

- Identify Class **Attributes**.
    - Specify the data type.

- UML **State** Diagram.
    - Model the high level state transition for a single auction only.

- UML **Activity** Diagram.
    - Model the process of a seller starting an auction.

- Identify Class **Operations**.
    - Specify the return data type.

- UML **Communication** Diagram.
    - Model the process of a two buyers bidding for an item and one of them being victorious.

- UML **Sequence** Diagram.
    - Model the process of a two buyers bidding for an item and one of them being victorious.

---

### Details of Part 2:

Working in groups of two or three, your prototype should be developed as a Console Application, using either Eclipse (Oxygen) and written in Java or Visual Studio 2017 and written in C#.

Every UML diagram in the model solution is important as each one models some aspect of the eAuction system. However, the most important diagram is the Class diagram as it forms not only the starting point of your prototype’s implementation but also models the majority of the eAuction system’s structure.

Ensure that your prototype is able to execute the tasks that are isolated in the Use Case diagram. The specific steps involved in executing these tasks are described either in the requirements document or the model solution. Where steps are poorly described you must use common sense / assumptions to implement those steps.

The eAuction system will require a menu system that should be implemented in the System class. You are not required to store data (i.e. a Database or Comma Separated Value .csv file). You can as an alternative, hard code test data for Users, Auctions etc.

The requirements document above is only an overview of the system. It does not incorporate every aspect of the system’s functionality. You must use common sense / assumptions along with the OOAD process to define parts of the system that are poorly described.

You should produce..

- Console Application, using either written in Java or in C#
