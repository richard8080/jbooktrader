package com.jbooktrader.strategy.base;

import com.ib.client.*;
import com.jbooktrader.platform.chart.*;
import com.jbooktrader.platform.commission.*;
import com.jbooktrader.platform.model.*;
import com.jbooktrader.platform.optimizer.*;
import com.jbooktrader.platform.schedule.*;
import com.jbooktrader.platform.strategy.*;
import com.jbooktrader.platform.util.*;

/**
 *
 */
public abstract class StrategyES extends Strategy {
	
	protected static final double BIDASKSPREAD = 0.25; // prevalent spread between best bid and best ask
    
	/*
	 * MARGIN REQUIREMENTS for ES: GLOBEX as of 13-July-2009
	 * Source: http://www.interactivebrokers.com/en/p.php?f=margin&ib_entity=llc
	 * 
	 * Initial Intra-day: $2,813
	 * Intra-day Maintenance: $2,250
	 * Initial Overnight: $5,625
	 * Overnight Maintenance: $4,500
	 */

	protected StrategyES(StrategyParams optimizationParams) throws JBookTraderException {
        super(optimizationParams);
        // Specify the contract to trade
        Contract contract = ContractFactory.makeFutureContract("ES", "GLOBEX");
        // Define trading schedule
        TradingSchedule tradingSchedule = new TradingSchedule("10:00", "15:25", "America/New_York");
        int multiplier = 50;// contract multiplier
        Commission commission = CommissionFactory.getBundledNorthAmericaFutureCommission();
        setStrategy(contract, tradingSchedule, multiplier, commission, BIDASKSPREAD, BarSize.Minute1);
    }

}
