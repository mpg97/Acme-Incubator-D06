/*
 * CustomCommand.java
 *
 * Copyright (c) 2019 Rafael Corchuelo.
 *
 * In keeping with the traditional purpose of furthering education and research, it is
 * the policy of the copyright owner to permit non-commercial use and redistribution of
 * this software. It has been tested carefully, but it is not guaranteed for any particular
 * purposes. The copyright owner does not offer any warranties or representations, nor do
 * they accept any liabilities with respect to them.
 */

package acme.components;

import acme.framework.components.Command;

public enum CustomCommand implements Command {

	LIST_BY_TICKER, LIST_BY_CREATION_DATE, LIST_ACTIVE, LIST_BY_STARS, LIST_BY_SECTOR, LIST_MINE, LIST_APPLIED_TO_MINE, LIST_INVOLVED, LIST_NOT_INVOLVED, LIST_BY_DISCUSSION, POST, LIST_BY_INVESTMENT, APPLY, LIST_APPLIED_TO_MINE_GROUPED_BY_TICKER, LIST_APPLIED_TO_MINE_GROUPED_BY_CREATION_DATE
}
